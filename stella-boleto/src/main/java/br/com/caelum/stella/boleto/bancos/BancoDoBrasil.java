package br.com.caelum.stella.boleto.bancos;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.CriacaoBoletoException;
import br.com.caelum.stella.boleto.Emissor;

/**
 * Gera dados de um boleto relativos ao Banco do Brasil.
 *
 * @see <a *
 *      href="http://stella.caelum.com.br/boleto-setup.html">http://stella.caelum
 *      * .com.br/boleto-setup.html< /a>
 *
 * @author Cauê Guerra
 * @author Paulo Silveira
 *
 */
public class BancoDoBrasil implements Banco {

    private static final String NUMERO_BB = "001";

    private final DVGenerator dvGenerator = new DVGenerator();

    public String geraCodigoDeBarrasPara(Boleto boleto) {
        StringBuilder codigoDeBarras = new StringBuilder();
        codigoDeBarras.append(getNumeroFormatado());
        codigoDeBarras.append(String.valueOf(boleto.getCodEspecieMoeda()));
        // Digito Verificador sera inserido aqui.

        codigoDeBarras.append(boleto.getFatorVencimento());
        codigoDeBarras.append(boleto.getValorFormatado());

        Emissor emissor = boleto.getEmissor();
        // CAMPO LIVRE
        if (emissor.getNumConvenio() < 1000000) {
            if (emissor.getCarteira() == 16 || emissor.getCarteira() == 18) {
                codigoDeBarras
                        .append(getNumConvenioDoEmissorFormatado(emissor));
                codigoDeBarras
                        .append(getSomenteNossoNumeroDoEmissorFormatado(emissor));
                codigoDeBarras.append("21");
            } else {
                codigoDeBarras
                        .append(getSomenteNossoNumeroDoEmissorFormatado(emissor));
                codigoDeBarras.append(emissor.getAgenciaFormatado());
                codigoDeBarras.append(emissor.getCedente());
                codigoDeBarras.append(boleto.getBanco()
                        .getCarteiraDoEmissorFormatado(emissor));
            }
        } else if (emissor.getCarteira() == 17 || emissor.getCarteira() == 18) {
            codigoDeBarras.append("000000");
            codigoDeBarras.append(getNumConvenioDoEmissorFormatado(emissor));
            codigoDeBarras.append(getSomenteNossoNumeroDoEmissorFormatado(emissor)
                    .substring(7));
            codigoDeBarras.append(boleto.getBanco()
                    .getCarteiraDoEmissorFormatado(emissor));
        } else {
            throw new CriacaoBoletoException(
                    "Erro na geração do código de barras. Nenhuma regra se aplica. Verifique carteira e demais dados.");
        }

        codigoDeBarras.insert(4, this.dvGenerator.geraDVMod11(codigoDeBarras
                .toString()));

        String result = codigoDeBarras.toString();

        if (result.length() != 44) {
            throw new CriacaoBoletoException(
                    "Erro na geração do código de barras. Número de digitos diferente de 44. Verifique todos os dados.");
        }

        return result;
    }

    public String getNumeroFormatado() {
        return NUMERO_BB;
    }

    public java.net.URL getImage() {
        return getClass().getResource(
                String.format("/br/com/caelum/stella/boleto/img/%s.png",
                        getNumeroFormatado()));
    }

    public String getNumConvenioDoEmissorFormatado(Emissor emissor) {
        if (emissor.getNumConvenio() < 1000000) {
            return String.format("%06d", emissor.getNumConvenio());
        } else {
            return String.format("%07d", emissor.getNumConvenio());
        }
    }

    public String getContaCorrenteDoEmissorFormatado(Emissor emissor) {
        return String.format("%08d", emissor.getContaCorrente());
    }

    public String getCarteiraDoEmissorFormatado(Emissor emissor) {
        return String.format("%02d", emissor.getCarteira());
    }

    public String getSomenteNossoNumeroDoEmissorFormatado(Emissor emissor) {
        if (emissor.getCarteira() == 18) {
            return String.format("%017d", emissor.getNossoNumero());
        } else {
            return String.format("%011d", emissor.getNossoNumero());
        }
    }

		public String getNossoNumeroDoEmissorFormatado(Emissor emissor) {
			return getSomenteNossoNumeroDoEmissorFormatado(emissor);
		}

		public String getNumeroComDigitoFormatado() {
				return getNumeroFormatado();
		}

		public String getLegendaCarteiraDoEmissor(Emissor emissor) {
				return getCarteiraDoEmissorFormatado(emissor);
		}

		public String getAgenciaCodigoCedenteFormatado(Emissor emissor) {
				throw new UnsupportedOperationException("Esse banco não possui esta informação disponível.");
		}

}
