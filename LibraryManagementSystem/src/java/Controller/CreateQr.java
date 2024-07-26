/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionSQL;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Avindu Aloka
 */
public class CreateQr {

    private String qrText = "empty";
    private Statement state = null;
    private PreparedStatement prepair = null;
    ConnectionSQL conn = new ConnectionSQL();

    public CreateQr(String qrText) {
        this.qrText = qrText;
    }

    public void createQr() {
        String qrCodeText = qrText;
        String filePath = "C:\\Users\\Avindu Aloka\\Documents\\NetBeansProjects\\LibraryManagementSystem\\web\\assets\\QrImage\\JD.png";
        int size = 1125;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println(qrText);
        System.out.println("DONE");
    }

    private void createQRImage(File qrFile, String qrCodeText, int size, String fileType) {
        try {
            // Create the ByteMatrix for the QR-Code that encodes the given String
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
            // Make the BufferedImage that are to hold the QRCode
            int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            // Paint and save the image using the ByteMatrix
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, qrFile);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

    public void FileDeletion() {
        String filePath = "C:\\Users\\Avindu Aloka\\Documents\\NetBeansProjects\\LibraryManagementSystem\\web\\assets\\QrImage\\JD.png";
        Path pathToDelete = Paths.get(filePath); // Replace with your file path

        try {
            // Attempt to delete the file
            Files.deleteIfExists(pathToDelete);
            System.out.println("File deleted successfully.");
        } catch (IOException e) {
            System.out.println("Failed to delete the file: " + e.getMessage());
        }
    }

    public boolean SaveQrCode(int UserID, int ticketId) {
        boolean result = false;
        String imagePath = "C:\\Users\\Avindu Aloka\\Documents\\NetBeansProjects\\LibraryManagementSystem\\web\\assets\\QrImage\\JD.png";
        try {
            conn.ConnectionSuccess();
            state = conn.Con.createStatement();
            ResultSet res = state.executeQuery("SELECT MAX(`QrCodeID`) FROM qrcodes");
            int LastIntId = 0;
            while (res.next()) {
                LastIntId = res.getInt(1);
            }

            res.close();
            state.close();
            conn.ConnectionClose();

            if (isRealesed(UserID, ticketId)) {
                result = true;
            } else {
                conn.ConnectionSuccess();
                state = conn.Con.createStatement();

                LocalDateTime cDateTime = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(cDateTime);

                String TicketTokenPerfix = "TICSUF";

                prepair = conn.Con.prepareStatement("INSERT INTO qrcodes VALUES(?,?,?,?,?,?);");
                if (LastIntId == 0) {
                    prepair.setInt(1, 1);
                    LastIntId = 1;
                } else {
                    prepair.setInt(1, LastIntId + 1);
                    LastIntId++;
                }
                prepair.setInt(2, UserID);
                prepair.setString(3, TicketTokenPerfix + LastIntId + UserID);
                prepair.setTimestamp(4, timestamp);
                FileInputStream fis = new FileInputStream(imagePath);
                prepair.setBinaryStream(5, fis, fis.available());
                prepair.setInt(6, ticketId);

                int i = prepair.executeUpdate();
                System.out.println("Recode Success");

                result = false;

                res.close();
                state.close();
                conn.ConnectionClose();
            }

        } catch (Exception ex) {
            System.out.print(ex);
        }

        return result;
    }

    public boolean isRealesed(int userId, int ticketId) {
        boolean result = false;
        try {
            conn.ConnectionSuccess();
            state = conn.Con.createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM `library`.qrcodes WHERE `BookID` = " + ticketId + " AND `UserID` = " + userId + "");
            String TicketNo = null;
            while (res.next()) {
                TicketNo = res.getString(3);
            }

            if (TicketNo.equals(null) || TicketNo.trim().isEmpty() || TicketNo == null) {
                result = false;
            } else {
                result = true;
            }

            res.close();
            state.close();
            conn.ConnectionClose();

        } catch (Exception ex) {
            System.out.print(ex);
        }
        return result;
    }

    public void imageDataGet(int UserID, int FunId) {
        String outputPath = "C:\\Users\\Avindu Aloka\\Documents\\NetBeansProjects\\LibraryManagementSystem\\web\\assets\\QrImage\\JD.png";
        //byte[] imageData = null;
        try {
            // Assume 'connection' is your database connection
            conn.ConnectionSuccess();
            
            PreparedStatement stmt = conn.Con.prepareStatement("SELECT QrImage FROM `library`.qrcodes WHERE `UserID` = " + UserID + " AND `BookID` = " + FunId + "");
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InputStream binaryStream = rs.getBinaryStream("QrImage");

                // Save the binary stream as a file
                Path outputPathFile = Paths.get(outputPath);
                Files.createDirectories(outputPathFile.getParent()); // Create directories if they don't exist
                FileOutputStream fos = new FileOutputStream(outputPathFile.toFile());

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = binaryStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                System.out.println("Image saved successfully to: " + outputPath);

                stmt.close();
                rs.close();
                fos.close();
                conn.ConnectionClose();
            } else {
                System.out.println("Image not found for UserID: " + UserID + " and FunID: " + FunId);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*public static void main(String[] args) {
        CreateQr qr = new CreateQr("");
        qr.imageDataGet(2, 1);
    }*/
}
