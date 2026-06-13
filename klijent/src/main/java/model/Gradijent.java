/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Tijana
 */
public class Gradijent extends JPanel {

    private Color boja1 = new Color(0, 144, 231);
    private Color boja2 = new Color(102, 255, 255);

    public Color getBoja1() {
        return boja1;
    }

    public void setBoja1(Color boja1) {
        this.boja1 = boja1;
    }

    public Color getBoja2() {
        return boja2;
    }

    public void setBoja2(Color boja2) {
        this.boja2 = boja2;
    }

    public Gradijent() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradijent = new GradientPaint(0, 0, boja1, getWidth(), getHeight(), boja2);
        g2d.setPaint(gradijent);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}
