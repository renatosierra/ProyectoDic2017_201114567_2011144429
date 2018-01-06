/*
 * Created by JFormDesigner on Fri Jan 05 18:13:40 CST 2018
 */

package com.edd.player;

import java.awt.event.*;
import javax.swing.event.*;
import com.edd.player.DTO.DTOLogin;
import com.edd.player.LoginDialog;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * @author Walter Garcia
 */
public class Reproductor extends JFrame {
    public Reproductor() {
        initComponents();

        DefaultListModel<DTOLogin.Data.Year> model = new DefaultListModel();
        list1.setModel(model);



        DefaultListModel<DTOLogin.Data.Year.Genere.Artist.Album.Cancion> modelCanciones = new DefaultListModel();
        jListEnReproduccion.setModel(modelCanciones);





        LoginDialog dialog = new LoginDialog();
        dialog.setSize(400,200);
        dialog.setLocationRelativeTo(null);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                DTOLogin dto = dialog.getData();
                DefaultListModel<DTOLogin.Data.Year> model = (DefaultListModel<DTOLogin.Data.Year>)list1.getModel();
                for(com.edd.player.DTO.DTOLogin.Data.Year year : dto.data.getYears()){
                    model.addElement(year);
                }
            }
        });
        dialog.setVisible(true);
    }

    public static void main(String[] args){
        Reproductor ventana = new Reproductor();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void btnPlay_PauseActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnNextActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnPreviousActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void btnSuffleActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void list1ValueChanged(ListSelectionEvent e) {
        DTOLogin.Data.Year year = (DTOLogin.Data.Year) list1.getSelectedValue();
        DefaultListModel<DTOLogin.Data.Year.Genere> modelGeneres = new DefaultListModel();
        jListGeneros.setModel(modelGeneres);
        if(year != null){
            for(com.edd.player.DTO.DTOLogin.Data.Year.Genere genere : year.getGeneros()){
                modelGeneres.addElement(genere);
            }
        }

    }

    private void jListGenerosValueChanged(ListSelectionEvent e) {
        DTOLogin.Data.Year.Genere genere = (DTOLogin.Data.Year.Genere) jListGeneros.getSelectedValue();
        DefaultListModel<DTOLogin.Data.Year.Genere.Artist> modelArtists = new DefaultListModel();
        jListArtistas.setModel(modelArtists);
        if(genere != null){
            button2.setText("Arbo B "+genere.genero+" ");
            for(com.edd.player.DTO.DTOLogin.Data.Year.Genere.Artist artist : genere.getArtistas()){
                modelArtists.addElement(artist);
            }
        }

    }

    private void jListArtistasValueChanged(ListSelectionEvent e) {
        // TODO add your code here
        DTOLogin.Data.Year.Genere.Artist artist = (DTOLogin.Data.Year.Genere.Artist) jListArtistas.getSelectedValue();
        DefaultListModel<DTOLogin.Data.Year.Genere.Artist.Album> modelAlbums = new DefaultListModel();
        jListAlbums.setModel(modelAlbums);

        if(artist != null){
            button3.setText("ABB "+artist.artista);
            for(com.edd.player.DTO.DTOLogin.Data.Year.Genere.Artist.Album album : artist.getAlbums()){
                modelAlbums.addElement(album);
            }
        }
    }

    private void jListAlbumsValueChanged(ListSelectionEvent e) {
        DTOLogin.Data.Year.Genere.Artist.Album album = (DTOLogin.Data.Year.Genere.Artist.Album) jListAlbums.getSelectedValue();
        DefaultListModel<DTOLogin.Data.Year.Genere.Artist.Album.Cancion> modelCanciones = new DefaultListModel();
        jListCanciones.setModel(modelCanciones);
        if(album != null){
            button3.setText("Lista "+album.nombre);
            for(com.edd.player.DTO.DTOLogin.Data.Year.Genere.Artist.Album.Cancion cancion : album.getCanciones()){
                modelCanciones.addElement(cancion);
            }
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button1MouseReleased(MouseEvent e) {
        // TODO add your code here
        try{
            Graphviz graph = new Graphviz();
            graph.CrearGrafo(HttpHelper.getMatrix());
        }catch (Exception ex){
            System.out.print(ex.getMessage().toString());
        }
    }

    private void btnSuffleMouseReleased(MouseEvent e) {
        // TODO add your code here
        DefaultListModel<DTOLogin.Data.Year.Genere.Artist.Album.Cancion> modelCanciones =  (DefaultListModel<DTOLogin.Data.Year.Genere.Artist.Album.Cancion>)jListEnReproduccion.getModel();

        com.edd.player.DTO.DTOLogin.Data.Year.Genere.Artist.Album.Cancion cancion = (com.edd.player.DTO.DTOLogin.Data.Year.Genere.Artist.Album.Cancion)jListCanciones.getSelectedValue();
        if(cancion !=null){

            modelCanciones.addElement(cancion);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Erik Sierra
        panel1 = new JPanel();
        lblCaratula = new JLabel();
        panel2 = new JPanel();
        btnPlay_Pause = new JButton();
        btnNext = new JButton();
        btnPrevious = new JButton();
        SliderProgreso = new JSlider();
        btnMute = new JButton();
        SliderVolume = new JSlider();
        lblTitulo = new JLabel();
        lblArtista = new JLabel();
        jLabelTranscurrido = new JLabel();
        jLabelTiempo = new JLabel();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        scrollPane2 = new JScrollPane();
        jListGeneros = new JList();
        scrollPane3 = new JScrollPane();
        jListArtistas = new JList();
        scrollPane4 = new JScrollPane();
        jListAlbums = new JList();
        scrollPane5 = new JScrollPane();
        jListCanciones = new JList();
        scrollPane6 = new JScrollPane();
        jListPlaylist = new JList();
        scrollPane7 = new JScrollPane();
        jListEnReproduccion = new JList();
        btnSuffle = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(51, 51, 55));

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(null);
            panel1.add(lblCaratula);
            lblCaratula.setBounds(0, 400, 205, 195);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(5, 5, 205, 595);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(37, 38, 38));
            panel2.setLayout(null);

            //---- btnPlay_Pause ----
            btnPlay_Pause.setOpaque(false);
            btnPlay_Pause.setBackground(new Color(37, 38, 38));
            btnPlay_Pause.addActionListener(e -> btnPlay_PauseActionPerformed(e));
            panel2.add(btnPlay_Pause);
            btnPlay_Pause.setBounds(495, 10, 45, 45);

            //---- btnNext ----
            btnNext.setDefaultCapable(false);
            btnNext.setOpaque(false);
            btnNext.setBackground(new Color(37, 38, 38));
            btnNext.addActionListener(e -> btnNextActionPerformed(e));
            panel2.add(btnNext);
            btnNext.setBounds(555, 10, 45, 45);

            //---- btnPrevious ----
            btnPrevious.setOpaque(false);
            btnPrevious.setBackground(new Color(37, 38, 38));
            btnPrevious.addActionListener(e -> btnPreviousActionPerformed(e));
            panel2.add(btnPrevious);
            btnPrevious.setBounds(435, 10, 45, 45);

            //---- SliderProgreso ----
            SliderProgreso.setOpaque(false);
            SliderProgreso.setValue(0);
            panel2.add(SliderProgreso);
            SliderProgreso.setBounds(415, 75, 220, SliderProgreso.getPreferredSize().height);

            //---- btnMute ----
            btnMute.setOpaque(false);
            btnMute.setBackground(new Color(37, 38, 38));
            panel2.add(btnMute);
            btnMute.setBounds(790, 10, 45, 40);

            //---- SliderVolume ----
            SliderVolume.setValue(70);
            SliderVolume.setOpaque(false);
            panel2.add(SliderVolume);
            SliderVolume.setBounds(845, 30, 140, SliderVolume.getPreferredSize().height);
            panel2.add(lblTitulo);
            lblTitulo.setBounds(15, 15, 225, 25);
            panel2.add(lblArtista);
            lblArtista.setBounds(15, 70, 225, 25);
            panel2.add(jLabelTranscurrido);
            jLabelTranscurrido.setBounds(360, 70, 40, 15);
            panel2.add(jLabelTiempo);
            jLabelTiempo.setBounds(640, 75, 40, 16);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2);
        panel2.setBounds(-5, 600, 1010, 115);

        //======== panel3 ========
        {
            panel3.setLayout(null);

            //======== scrollPane1 ========
            {

                //---- list1 ----
                list1.setBackground(new Color(23, 23, 23));
                list1.setForeground(Color.white);
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list1.addListSelectionListener(e -> list1ValueChanged(e));
                scrollPane1.setViewportView(list1);
            }
            panel3.add(scrollPane1);
            scrollPane1.setBounds(0, 0, 75, 305);

            //======== scrollPane2 ========
            {

                //---- jListGeneros ----
                jListGeneros.setBackground(new Color(23, 23, 23));
                jListGeneros.setForeground(Color.white);
                jListGeneros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jListGeneros.addListSelectionListener(e -> jListGenerosValueChanged(e));
                scrollPane2.setViewportView(jListGeneros);
            }
            panel3.add(scrollPane2);
            scrollPane2.setBounds(75, 0, 115, 305);

            //======== scrollPane3 ========
            {

                //---- jListArtistas ----
                jListArtistas.setBackground(new Color(23, 23, 23));
                jListArtistas.setForeground(Color.white);
                jListArtistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jListArtistas.addListSelectionListener(e -> jListArtistasValueChanged(e));
                scrollPane3.setViewportView(jListArtistas);
            }
            panel3.add(scrollPane3);
            scrollPane3.setBounds(190, 0, 200, 305);

            //======== scrollPane4 ========
            {

                //---- jListAlbums ----
                jListAlbums.setBackground(new Color(23, 23, 23));
                jListAlbums.setForeground(Color.white);
                jListAlbums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jListAlbums.addListSelectionListener(e -> jListAlbumsValueChanged(e));
                scrollPane4.setViewportView(jListAlbums);
            }
            panel3.add(scrollPane4);
            scrollPane4.setBounds(390, 0, 200, 305);

            //======== scrollPane5 ========
            {

                //---- jListCanciones ----
                jListCanciones.setBackground(new Color(23, 23, 23));
                jListCanciones.setForeground(Color.white);
                jListCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                scrollPane5.setViewportView(jListCanciones);
            }
            panel3.add(scrollPane5);
            scrollPane5.setBounds(590, 0, 200, 305);

            //======== scrollPane6 ========
            {

                //---- jListPlaylist ----
                jListPlaylist.setBackground(new Color(23, 23, 23));
                jListPlaylist.setForeground(Color.white);
                scrollPane6.setViewportView(jListPlaylist);
            }
            panel3.add(scrollPane6);
            scrollPane6.setBounds(0, 305, 390, 240);

            //======== scrollPane7 ========
            {

                //---- jListEnReproduccion ----
                jListEnReproduccion.setBackground(new Color(23, 23, 23));
                jListEnReproduccion.setForeground(Color.white);
                scrollPane7.setViewportView(jListEnReproduccion);
            }
            panel3.add(scrollPane7);
            scrollPane7.setBounds(395, 305, 395, 240);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel3);
        panel3.setBounds(210, 55, 795, 545);

        //---- btnSuffle ----
        btnSuffle.addActionListener(e -> btnSuffleActionPerformed(e));
        btnSuffle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                btnSuffleMouseReleased(e);
            }
        });
        contentPane.add(btnSuffle);
        btnSuffle.setBounds(945, 10, 50, 35);

        //---- button1 ----
        button1.setText("Ver Matriz");
        button1.addActionListener(e -> button1ActionPerformed(e));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                button1MouseReleased(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(220, 15, 183, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("Arbol B");
        contentPane.add(button2);
        button2.setBounds(410, 15, 180, button2.getPreferredSize().height);

        //---- button3 ----
        button3.setText("ABB");
        contentPane.add(button3);
        button3.setBounds(605, 15, 195, button3.getPreferredSize().height);

        //---- button4 ----
        button4.setText("Canciones");
        contentPane.add(button4);
        button4.setBounds(805, 15, 130, button4.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Erik Sierra
    private JPanel panel1;
    private JLabel lblCaratula;
    private JPanel panel2;
    private JButton btnPlay_Pause;
    private JButton btnNext;
    private JButton btnPrevious;
    private JSlider SliderProgreso;
    private JButton btnMute;
    private JSlider SliderVolume;
    private JLabel lblTitulo;
    private JLabel lblArtista;
    private JLabel jLabelTranscurrido;
    private JLabel jLabelTiempo;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JList list1;
    private JScrollPane scrollPane2;
    private JList jListGeneros;
    private JScrollPane scrollPane3;
    private JList jListArtistas;
    private JScrollPane scrollPane4;
    private JList jListAlbums;
    private JScrollPane scrollPane5;
    private JList jListCanciones;
    private JScrollPane scrollPane6;
    private JList jListPlaylist;
    private JScrollPane scrollPane7;
    private JList jListEnReproduccion;
    private JButton btnSuffle;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
