/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Estoque;
import model.bean.Livro;
import model.dao.EmprestimoDAO;
import model.dao.EstoqueDAO;
import model.dao.LivroDAO;
import model.dao.UsuarioDAO;

/**
 *
 * @author Rocha
 */
public class ViewAluguelLivro extends javax.swing.JFrame {
    private List<Livro> livrosSelecionados= new ArrayList<>();
    private UsuarioDAO daousuario = new UsuarioDAO();
    private ViewHomeUsuario enviaUsuario;
    private ViewConfirmEmprestimo enviaCpfConfirma;
    private String cpfUsuarioLogado;
    

    /**
     * Creates new form ViewAluguelLivro
     */
    public ViewAluguelLivro() {
        initComponents();
        
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosAluguel.getModel();
        jTableLivrosAluguel.setRowSorter(new TableRowSorter(modelo));
        livrosSelecionados.clear();
        txtQuantidade.setText(String.valueOf(livrosSelecionados.size()));
        readJTable();
        
    }
    
    public ViewAluguelLivro(String cpf) {
        initComponents();
        this.cpfUsuarioLogado = cpf;
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosAluguel.getModel();
        jTableLivrosAluguel.setRowSorter(new TableRowSorter(modelo));
        livrosSelecionados.clear();
        txtQuantidade.setText(String.valueOf(livrosSelecionados.size()));
        readJTable();
        
    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosAluguel.getModel();
        modelo.setNumRows(0);
        LivroDAO dao = new LivroDAO();

        for (Livro n : dao.findAll()) {
            modelo.addRow(new Object[]{
                n.getIsbn(),
                n.getTitulo(),
                n.getAutor(),
                n.getAno(),
                n.getEstoque().getQuantidade()
            });
        }
    }
    public void readJTableSearch(String str) {
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosAluguel.getModel();
        modelo.setNumRows(0);
        LivroDAO dao = new LivroDAO();

        for (Livro n : dao.search(str)) {
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLivrosAluguel = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();
        txtTitulo = new javax.swing.JTextField();
        txtIsbn = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        jButtonAlugar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jButtonFiltrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonRefresh = new javax.swing.JButton();
        txtQuantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButtonPlus = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTableLivrosAluguel.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableLivrosAluguel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLivrosAluguelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLivrosAluguel);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        txtTitulo.setEditable(false);
        txtTitulo.setBackground(new java.awt.Color(204, 204, 204));
        txtTitulo.setForeground(new java.awt.Color(51, 51, 51));

        txtIsbn.setEditable(false);
        txtIsbn.setBackground(new java.awt.Color(204, 204, 204));
        txtIsbn.setForeground(new java.awt.Color(51, 51, 51));

        txtAutor.setEditable(false);
        txtAutor.setBackground(new java.awt.Color(204, 204, 204));
        txtAutor.setForeground(new java.awt.Color(51, 51, 51));

        txtAno.setEditable(false);
        txtAno.setBackground(new java.awt.Color(204, 204, 204));
        txtAno.setForeground(new java.awt.Color(51, 51, 51));

        jButtonAlugar.setText("Alugar");
        jButtonAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlugarActionPerformed(evt);
            }
        });

        jButtonFiltrar.setText("Filtrar");
        jButtonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Título");

        jLabel2.setText("Autor");

        jLabel3.setText("Ano");

        jLabel4.setText("ISBN");

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/585e4831cb11b227491c338e (1).png"))); // NOI18N
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        txtQuantidade.setEditable(false);
        txtQuantidade.setBackground(new java.awt.Color(204, 204, 204));
        txtQuantidade.setForeground(new java.awt.Color(51, 51, 51));
        txtQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });

        jLabel5.setText("Livros selecionados");

        jButtonPlus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonPlus.setText("+");
        jButtonPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(272, 272, 272))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonVoltar)
                                            .addComponent(txtTitulo)
                                            .addComponent(txtIsbn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonFiltrar)
                                                .addGap(0, 9, Short.MAX_VALUE)))
                                        .addGap(61, 61, 61)
                                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPlus, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(29, 29, 29)
                        .addComponent(jButtonAlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAlugar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonFiltrar)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableLivrosAluguelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLivrosAluguelMouseClicked
        // TODO add your handling code here:
        Livro livro = new Livro();
        Estoque estoque = new Estoque();


        livro.setIsbn((String) jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(), 0));
        livro.setTitulo((String) jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(),1));
        livro.setAutor((String) jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(), 2));
        livro.setAno((int) jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(), 3));
        estoque.setQuantidade((int) jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(), 4));
        
        
        
        txtIsbn.setText(livro.getIsbn());
        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtAno.setText(String.valueOf(livro.getAno()));


    }//GEN-LAST:event_jTableLivrosAluguelMouseClicked

    private void jButtonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarActionPerformed
        // TODO add your handling code here:
        readJTableSearch(txtBuscar.getText());
        
    }//GEN-LAST:event_jButtonFiltrarActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        // TODO add your handling code here:
        readJTable();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        enviaUsuario = new ViewHomeUsuario();
        enviaUsuario.setVisible(true);
       // enviaUsuario.recebeNome(daousuario.findByCpf(cpfUsuario));
        this.dispose();
        
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_formWindowOpened

    private void jButtonAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlugarActionPerformed
        // TODO add your handling code here:
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        boolean temEmprestimo = emprestimoDAO.usuarioJaTemEmprestimo(cpfUsuarioLogado);
        if(temEmprestimo){
            JOptionPane.showMessageDialog(null, "Você já tem um emprestimo ou já tem um pedido!");
        }
        if(Integer.parseInt(txtQuantidade.getText())>0 && !temEmprestimo){
           ViewConfirmEmprestimo enviaLista;
           enviaLista =new ViewConfirmEmprestimo(livrosSelecionados,cpfUsuarioLogado);
           enviaLista.setVisible(true);
           
       }
        
       
    }//GEN-LAST:event_jButtonAlugarActionPerformed

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void jButtonPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlusActionPerformed
        // TODO add your handling code here:
        Livro livro = new Livro();
        LivroDAO livrodao = new LivroDAO();
        livro = livrodao.findLivroByIsbn((String) jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(), 0));
        int i = 0;
        if(livrosSelecionados.size()<3){
            for(Livro n : livrosSelecionados){
                if(n.getIsbn().equals(livro.getIsbn())){
                    JOptionPane.showMessageDialog(null,"Livro já selecionado!");
                    i =1;
                    break;
                }
                
            }
            if(((int)jTableLivrosAluguel.getValueAt(jTableLivrosAluguel.getSelectedRow(), 4))<=0){
                i=1;
                JOptionPane.showMessageDialog(null,"Livro esgotado!");
            }
            if(i==0){
                livrosSelecionados.add(livro);          
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"Número máximo de livros alugados atingido!");
        }
        
        
        txtQuantidade.setText(String.valueOf(livrosSelecionados.size()));
    

        
    }//GEN-LAST:event_jButtonPlusActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        txtQuantidade.setText(String.valueOf(livrosSelecionados.size()));
    }//GEN-LAST:event_formWindowGainedFocus

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        emprestimoDAO.devolveEmprestimo("777.777.777-77");
        
        
        
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
            java.util.logging.Logger.getLogger(ViewAluguelLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAluguelLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAluguelLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAluguelLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAluguelLivro().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAlugar;
    private javax.swing.JButton jButtonFiltrar;
    private javax.swing.JButton jButtonPlus;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLivrosAluguel;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables


}