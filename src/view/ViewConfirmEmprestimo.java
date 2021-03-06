/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Estoque;
import model.bean.Livro;
import model.bean.PedidoEmprestimoDTO;
import model.dao.EmprestimoDAO;
import model.dao.EstoqueDAO;
import model.dao.LivroDAO;

/**
 *
 * @author Rocha
 */
public class ViewConfirmEmprestimo extends javax.swing.JFrame {
    
    private List<Livro> livrosSelecionadosc = new ArrayList<>();
    private Livro livro = new Livro();
    private LivroDAO daoLivro = new LivroDAO();
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private String cpfUsuarioLogado;

    /**
     * Creates new form ViewConfirmEmprestimo
     */
    public ViewConfirmEmprestimo() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosSelecionados.getModel();
        jTableLivrosSelecionados.setRowSorter(new TableRowSorter(modelo));
        readJTable();
    }

    public ViewConfirmEmprestimo(List<Livro> livros,String cpf) {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosSelecionados.getModel();
        jTableLivrosSelecionados.setRowSorter(new TableRowSorter(modelo));
        this.setLista(livros);
        this.cpfUsuarioLogado=cpf;
        readJTable();
    }

    public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) jTableLivrosSelecionados.getModel();
        modelo.setNumRows(0);

        for (Livro n : livrosSelecionadosc) {
            modelo.addRow(new Object[]{
                n.getIsbn(),
                n.getTitulo(),
                n.getAutor(),
                n.getAno()
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLivrosSelecionados = new javax.swing.JTable();
        jButtonConfirma = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableLivrosSelecionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Título", "Autor", "Ano"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLivrosSelecionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLivrosSelecionadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLivrosSelecionados);

        jButtonConfirma.setText("Confirmar Empréstimo");
        jButtonConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmaActionPerformed(evt);
            }
        });

        jButtonRemove.setText("Remover livro da lista");
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButtonRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonConfirma)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemove)
                    .addComponent(jButtonConfirma))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        // TODO add your handling code here:
        livro.setIsbn((String) jTableLivrosSelecionados.getValueAt(jTableLivrosSelecionados.getSelectedRow(), 0));
        if (jTableLivrosSelecionados.getSelectedRow() != -1) {
            for (Livro n : livrosSelecionadosc) {
                if (n.getIsbn().equals(livro.getIsbn())) {
                    livrosSelecionadosc.remove(n);
                    readJTable();
                    JOptionPane.showMessageDialog(null, "Livro retirado com sucesso!");
                    
                    
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum livro selecionado!");
        }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jTableLivrosSelecionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLivrosSelecionadosMouseClicked
        // TODO add your handling code here:

        
    }//GEN-LAST:event_jTableLivrosSelecionadosMouseClicked

    private void jButtonConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmaActionPerformed
        emprestimoDAO.criaPedidoEmprestimo(cpfUsuarioLogado, livrosSelecionadosc);
        
        JOptionPane.showMessageDialog(null, "Pedido de Emprestimo enviado!");
        this.dispose();
      
        
    }//GEN-LAST:event_jButtonConfirmaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        
        
        
        //System.out.println( java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        
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
            java.util.logging.Logger.getLogger(ViewConfirmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewConfirmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewConfirmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewConfirmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewConfirmEmprestimo().setVisible(true);
            }
        });
    }

    public void setLista(List<Livro> livros) {
        this.livrosSelecionadosc = livros;
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirma;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLivrosSelecionados;
    // End of variables declaration//GEN-END:variables
}
