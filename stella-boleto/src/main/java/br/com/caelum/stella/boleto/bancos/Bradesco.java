package br.com.caelum.stella.boleto.bancos;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.CriacaoBoletoException;
import br.com.caelum.stella.boleto.Emissor;

/**
 * Gera dados de um boleto relativos ao Banco Bradesco.
 *
 * @see <a *
 *      href="http://stella.caelum.com.br/boleto-setup.html">http://stella.caelum
 *      * .com.br/boleto-setup.html< /a>
 *
 * @see <a * href=
 *      "http://www.bradesco.com.br/br/pj/conteudo/sol_rec/pdf/manualtecnico.pdf"
 *      >MANUAL * DO BLOQUETO DE COBRANÇA< /a>
 *
 * @author Leonardo Bessa
 *
 */
public class Bradesco implements Banco {

    private static final String NUMERO_BRADESCO = "237";

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
        codigoDeBarras.append(emissor.getAgenciaFormatado());
        codigoDeBarras.append(getCarteiraDoEmissorFormatado(emissor));
        codigoDeBarras.append(getSomenteNossoNumeroDoEmissorFormatado(emissor));
        codigoDeBarras.append(getContaCorrenteDoEmissorFormatado(emissor));
        codigoDeBarras.append("0");

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
        return NUMERO_BRADESCO;
    }

    public java.net.URL getImage() {
        return getClass().getResource(
                String.format("/br/com/caelum/stella/boleto/img/%s.png",
                        getNumeroFormatado()));
    }

    public String getNumConvenioDoEmissorFormatado(Emissor emissor) {
        return String.format("%07d", emissor.getNumConvenio());
    }

    public String getContaCorrenteDoEmissorFormatado(Emissor emissor) {
        return String.format("%07d", emissor.getContaCorrente());
    }

    public String getCarteiraDoEmissorFormatado(Emissor emissor) {
        return String.format("%02d", emissor.getCarteira());
    }

    public String getSomenteNossoNumeroDoEmissorFormatado(Emissor emissor) {
        return String.format("%011d", emissor.getNossoNumero());
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
