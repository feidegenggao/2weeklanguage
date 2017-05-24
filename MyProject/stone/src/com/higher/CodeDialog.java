package com.higher;

import com.higher.util.Log;

import javax.swing.*;
import java.io.*;

/**
 * 代码输入框
 * Created by lxf on 2017/5/24.
 */
public class CodeDialog extends Reader {
    private String codeBuffer;
    private int pos = 0;

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (codeBuffer == null) {
            String in = showDialog();
            if (in == null) {
                Log.i("请输入代码");
                return 0;
            } else {
                Log.i(in);
                codeBuffer = in + "\n";
                pos = 0;
            }
        }

        int size = 0;
        int bufferLen = codeBuffer.length();
        while (size < len && pos < bufferLen) {
            cbuf[off + size] = codeBuffer.charAt(pos++);
            size++;
        }

        if (pos == bufferLen) {
            codeBuffer = null;
        }
        return size;
    }

    private String showDialog() {
        JTextArea jTextArea = new JTextArea(20, 40);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        int result = JOptionPane.showOptionDialog(null, jScrollPane,
                "请输入代码", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            return jTextArea.getText();
        } else {
            return null;
        }
    }

    @Override
    public void close() throws IOException {

    }

    public static Reader file() {
        JFileChooser jFileChooser = new JFileChooser();
        try {
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                return new BufferedReader(new FileReader(jFileChooser.getSelectedFile()));

            } else {
                throw new FileNotFoundException("no file specified");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
