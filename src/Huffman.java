import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Huffman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Huffman frame = new Huffman();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Huffman() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(158, 82, 235, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter file path");
		lblNewLabel.setBounds(25, 84, 115, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaticHuffman x = new StaticHuffman();
				x.Compress(textField.getText().toString());
				JOptionPane.showMessageDialog(null, "Compressed successfully!");
			}
		});
		btnCompress.setBounds(91, 159, 117, 25);
		contentPane.add(btnCompress);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaticHuffman x = new StaticHuffman();
				x.decompress(textField.getText().toString());
				JOptionPane.showMessageDialog(null, "Decompressed successfully!");
			}
		});
		btnDecompress.setBounds(279, 159, 130, 25);
		contentPane.add(btnDecompress);
	}
}
