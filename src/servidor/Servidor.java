/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import transferencia.Arquivo;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Drew
 * @since  09/07/2019
 */
public class Servidor {
    
    public static void main(String[] args) {
        Servidor s = new Servidor();
        
        s.inicia();
    }
    
    public void inicia() {
        try {
            
            ServerSocket servSocket = new ServerSocket(5566);
            System.out.println("Servidor iniciado. Aguardando arquivo.");

            while(true) {

                Socket socket = servSocket.accept();
            
                DataInputStream oDtaInput = new DataInputStream(socket.getInputStream());
                
                int tamanho = oDtaInput.readInt();
                byte[] tese = new byte[tamanho];

                int posicao = 0;
                byte[] buffer = new byte[1024];
                while((oDtaInput.read(buffer)) > 0) {
                    for(int i = 0; i < buffer.length; i++) {
                        if(posicao == tamanho) {
                            break;
                        }
                        tese[posicao] = buffer[i];
                        posicao++;
                    }
                }

                Arquivo oArquivo = (Arquivo) getObjetoByte(tese);

                String sDir = oArquivo.getDiretorioDestino().endsWith("/") ? oArquivo.getDiretorioDestino() + oArquivo.getNome() :
                                                                             oArquivo.getDiretorioDestino() + "/" + oArquivo.getNome();
                System.out.println("Arquivo sendo escrito...");

                FileOutputStream oFileD = new FileOutputStream(sDir);//FileOutputStream utilizado quando a saida eh baseada em arquivos
                oFileD.write(oArquivo.getConteudo());
                oFileD.close();
                System.out.println("Arquivo Inserido com sucesso.");
                System.out.println("Caminho Relativo: " + sDir);

            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retorna o objeto a partir do array de bytes fornecido
     * 
     * @param oObjetoByte - Array de bytes do objeto
     * @return Object
     */
    private Object getObjetoByte(byte[] oObjetoByte) {
        Object               oObj         = null;
        ByteArrayInputStream oByteInput   = null;
        ObjectInputStream    oObjectInput = null;
                
        try {
            oByteInput   = new ByteArrayInputStream(oObjetoByte);
            oObjectInput = new ObjectInputStream(oByteInput);
            oObj         = oObjectInput.readObject();
            
            oByteInput.close();
            oByteInput.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return oObj;
    }
}
