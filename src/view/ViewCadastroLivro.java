/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Estoque;
import model.bean.Livro;
import model.bean.Usuario;
import model.dao.EstoqueDAO;
import model.dao.LivroDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author Rocha
 */
public class ViewCadastroLivro extends javax.swing.JFrame {
    ViewQtdNova enviaLivro;
    Livro livro = new Livro();
    LivroDAO dao = new LivroDAO();
    Estoque estoque = new Estoque();
    EstoqueDAO daoEstoque = new EstoqueDAO();
    public ViewCadastroLivro(){
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTableLivros.getModel();
        jTableLivros.setRowSorter(new TableRowSorter(modelo));
        readJTable();
    }
    
    public void readJTable(){
        DefaultTableModel modelo = (DefaultTableModel)jTableLivros.getModel();
        modelo.setNumRows(0);
        LivroDAO dao = new LivroDAO();
        
        for( Livro n: dao.findAll()){
            modelo.addRow(new Object[]{
               n.getIsbn(),
               n.getTitulo(),
               n.getAutor(),
               n.getAno(),
               n.getEstoque().getQuantidade()
            });
        }    
    }
    
    
    
    
    /**
     * Creates new form ViewCadastroLivro
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLivros = new javax.swing.JTable();
        txtTituloLivro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonCadastro = new javax.swing.JButton();
        txtISBNLivro = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQtdLivro = new javax.swing.JTextField();
        jButtonAtualizar = new javax.swing.JButton();
        txtAno = new javax.swing.JFormattedTextField();
        txtAutorLivro = new javax.swing.JTextField();
        jButtonRefresh = new javax.swing.JButton();
        txtVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTableLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Título", "Autor", "Ano", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLivrosMouseClicked(evt);
            }
        });
        jTableLivros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableLivrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLivros);

        txtTituloLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloLivroActionPerformed(evt);
            }
        });

        jLabel1.setText("Título do livro");

        jLabel2.setText("Autor");

        jLabel3.setText("Ano");

        jLabel4.setText("ISBN");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Cadastro de Livro");

        jButtonCadastro.setText("Cadastrar");
        jButtonCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastroActionPerformed(evt);
            }
        });

        try {
            txtISBNLivro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-##-###-####-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Quantidade");

        txtQtdLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtdLivroActionPerformed(evt);
            }
        });

        jButtonAtualizar.setText("Editar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        try {
            txtAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtAutorLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAutorLivroActionPerformed(evt);
            }
        });

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/585e4831cb11b227491c338e (1).png"))); // NOI18N
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(30, 30));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(30, 30));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        txtVoltar.setText("Voltar");
        txtVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtISBNLivro)
                                .addComponent(txtTituloLivro, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 560, Short.MAX_VALUE)
                                .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(txtQtdLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(txtAutorLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(83, 83, 83)
                                        .addComponent(jButtonCadastro))
                                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(1, 92, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtVoltar)
                        .addGap(289, 289, 289)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVoltar)
                    .addComponent(jLabel5))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTituloLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAutorLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtISBNLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQtdLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCadastro)
                        .addComponent(jButtonAtualizar)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastroActionPerformed

        
        // TODO add your handling code here:
        Livro livro = new Livro();
        LivroDAO dao = new LivroDAO();
        livro.setTitulo(txtTituloLivro.getText());
        livro.setAutor(txtAutorLivro.getText());
        livro.setIsbn(txtISBNLivro.getText());
        livro.setAno(Integer.parseInt(txtAno.getText()));
        int quantidade = Integer.parseInt(txtQtdLivro.getText());
        dao.create(livro,quantidade);
        
        txtAno.setText("");
        txtAutorLivro.setText("");
        txtTituloLivro.setText("");
        txtISBNLivro.setText("");
        txtQtdLivro.setText("");
        readJTable();
        
        
        
        
        
    }//GEN-LAST:event_jButtonCadastroActionPerformed

    private void txtQtdLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtdLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtdLivroActionPerformed
    
    private void txtTituloLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloLivroActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        // TODO add your handling code here:S
            if(jTableLivros.getSelectedRow()!=-1){
            enviaLivro = new ViewQtdNova();
            this.dispose();
            enviaLivro.setVisible(true);
            enviaLivro.recebeLivro(livro, estoque);
            
         
        
    }//GEN-LAST:event_jButtonAtualizarActionPerformed
        else{
                JOptionPane.showMessageDialog(null,"nenhum livro selecionado!");
}
    }
    private void txtAutorLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAutorLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAutorLivroActionPerformed

    private void jTableLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLivrosMouseClicked
        // TODO add your handling code here:
        if(jTableLivros.getSelectedRow()!=-1){
            livro.setIsbn( jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 0).toString());
            livro.setTitulo( jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 1).toString());
            livro.setAutor( jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 2).toString());
            livro.setAno((int)jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 3));
            estoque.setId_estoque(1);
            estoque.setQuantidade((int) jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 4));
            livro.setEstoque(estoque);
            
            
        }
    }//GEN-LAST:event_jTableLivrosMouseClicked

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        // TODO add your handling code here:
        readJTable();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void txtVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVoltarActionPerformed
        // TODO add your handling code here:
        new ViewHomeAdm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtVoltarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        
   
    }//GEN-LAST:event_formWindowOpened

    private void jTableLivrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableLivrosKeyReleased
        // TODO add your handling code here:
        if(jTableLivros.getSelectedRow()!=-1){
            livro.setIsbn( jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 0).toString());
            livro.setTitulo( jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 1).toString());
            livro.setAutor( jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 2).toString());
            livro.setAno((int)jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 3));
            estoque.setId_estoque(1);
            estoque.setQuantidade((int) jTableLivros.getValueAt(jTableLivros.getSelectedRow(), 4));
            livro.setEstoque(estoque);
            
            
        }
    }//GEN-LAST:event_jTableLivrosKeyReleased
    
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
            java.util.logging.Logger.getLogger(ViewCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCadastroLivro().setVisible(true);
               
                
                
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonCadastro;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLivros;
    private javax.swing.JFormattedTextField txtAno;
    private javax.swing.JTextField txtAutorLivro;
    private javax.swing.JFormattedTextField txtISBNLivro;
    private javax.swing.JTextField txtQtdLivro;
    private javax.swing.JTextField txtTituloLivro;
    private javax.swing.JButton txtVoltar;
    // End of variables declaration//GEN-END:variables



}





