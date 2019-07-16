
package transferencia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Drew
 * @since  09/07/2019
 */
public class Arquivo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String sNome;
    private byte[] aConteudo;
    private String sDiretorioDestino;
    private Date   oDataHoraUpload;

    public String getNome() {
        return sNome;
    }

    public void setNome(String sNome) {
        this.sNome = sNome;
    }

    public byte[] getConteudo() {
        return aConteudo;
    }

    public void setConteudo(byte[] aConteudo) {
        this.aConteudo = aConteudo;
    }

    public String getDiretorioDestino() {
        return sDiretorioDestino;
    }

    public void setDiretorioDestino(String sDiretorioDestino) {
        this.sDiretorioDestino = sDiretorioDestino;
    }

    public Date getDataHoraUpload() {
        return oDataHoraUpload;
    }

    public void setDataHoraUpload(Date oDataHoraUpload) {
        this.oDataHoraUpload = oDataHoraUpload;
    }
    
}
