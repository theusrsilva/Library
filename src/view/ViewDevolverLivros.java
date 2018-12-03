/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.bean.Emprestimo;
import model.bean.Livro;
import model.bean.PedidoEmprestimoDTO;
import model.bean.Usuario;
import model.dao.EmprestimoDAO;
import model.dao.LivroDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author Rocha
 */
public class ViewDevolverLivros extends javax.swing.JFrame {
    private UsuarioDAO daousuario = new UsuarioDAO();
    private Usuario usuario = new Usuario();
    private ViewHomeUsuario enviaUsuario;
    private String cpfUsuarioLogado;
    private EmprestimoDAO daoEmprestimo = new EmprestimoDAO();
    private Date data = new Date();
    
    /**
     * Creates new form ViewDevolverLivros
     */
    public ViewDevolverLivros() {
        initComponents();
        readJTable();
    }
    
    public ViewDevolverLivros(String cpf) {
        initComponents();
        this.cpfUsuarioLogado = cpf;
        txtDataDev.setText(daoEmprestimo.getInfosEmprestimo(cpf).getDataPrevista().toString());
        txtDataEmprestimo.setText(daoEmprestimo.getInfosEmprestimo(cpf).getDataEmpresimo().toString());
        txtDataHoje.setText(data.toString());
        readJTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStatusLivros = new javax.swing.JTable();
        txtDataEmprestimo = new javax.swing.JTextField();
        txtDataDev = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonDevolver = new javax.swing.JButton();
        txtDataHoje = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jTableStatusLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título", "Autor", "Data de devolução prevista"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStatusLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableStatusLivrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableStatusLivros);

        txtDataEmprestimo.setEditable(false);
        txtDataEmprestimo.setBackground(new java.awt.Color(153, 153, 153));
        txtDataEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataEmprestimoActionPerformed(evt);
            }
        });

        txtDataDev.setEditable(false);
        txtDataDev.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setText("Data do empréstimo");

        jLabel4.setText("Data de devolução");

        jButtonDevolver.setText("Devolver");
        jButtonDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolverActionPerformed(evt);
            }
        });

        txtDataHoje.setEditable(false);
        txtDataHoje.setBackground(new java.awt.Color(153, 153, 153));

        jLabel7.setText("Data de hoje");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVoltar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(78, 78, 78)
                                                .addComponent(jLabel4))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(112, 112, 112)
                                                .addComponent(txtDataDev, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonDevolver)))))
                        .addGap(144, 144, 144)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataDev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDevolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        enviaUsuario = new ViewHomeUsuario(cpfUsuarioLogado);
        enviaUsuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jTableStatusLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStatusLivrosMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTableStatusLivrosMouseClicked

    private void txtDataEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataEmprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataEmprestimoActionPerformed

    private void jButtonDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolverActionPerformed
        // TODO add your handling code here:
        daoEmprestimo.devolveEmprestimo(cpfUsuarioLogado);
        readJTable();
        
    }//GEN-LAST:event_jButtonDevolverActionPerformed
    public void readJTable(){
        DefaultTableModel modelo = (DefaultTableModel)jTableStatusLivros.getModel();
        modelo.setNumRows(0);
        EmprestimoDAO dao = new EmprestimoDAO();
        Emprestimo Emprest = dao.getInfosEmprestimo(cpfUsuarioLogado);
        List<Livro> listaLivros = dao.findLivrosEmpretadosPorCpf(cpfUsuarioLogado);
        for( Livro n: listaLivros){
            modelo.addRow(new Object[]{
               n.getTitulo(),
               n.getAutor(),
               Emprest.getDataPrevista()
            });
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewDevolverLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDevolverLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDevolverLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDevolverLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDevolverLivros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDevolver;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStatusLivros;
    private javax.swing.JTextField txtDataDev;
    private javax.swing.JTextField txtDataEmprestimo;
    private javax.swing.JTextField txtDataHoje;
    // End of variables declaration//GEN-END:variables
}
