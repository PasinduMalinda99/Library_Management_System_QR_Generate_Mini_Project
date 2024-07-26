/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Avindu Aloka
 */
public class Qr {
    private int qrId;
    private byte[] qrImage;
    private LocalDateTime qrDateTime;

    public int getQrId() {
        return qrId;
    }

    public void setQrId(int qrId) {
        this.qrId = qrId;
    }

    public byte[] getQrImage() {
        return qrImage;
    }

    public void setQrImage(byte[] qrImage) {
        this.qrImage = qrImage;
    }

    public LocalDateTime getQrDateTime() {
        return qrDateTime;
    }

    public void setQrDateTime(LocalDateTime qrDateTime) {
        this.qrDateTime = qrDateTime;
    }
    
}
