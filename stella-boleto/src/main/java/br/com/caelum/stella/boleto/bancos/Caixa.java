package br.com.caelum.stella.boleto.bancos;

import java.net.URL;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.CriacaoBoletoException;
import br.com.caelum.stella.boleto.Emissor;

public class Caixa implements Banco {

    private static final String NUMERO_CAIXA = "104";
  	private static final String DIGITO_NUMERO_CAIXA = "0";
  	public static final String CARTEIRA_COBRANCA_RAPIDA = "9";
  	public static final String CARTEIRA_COBRANCA_SEM_REGISTRO = "82";
  	public static final String LEGENDA_CARTEIRA_COBRANCA_RAPIDA = "CR";
  	public static final String LEGENDA_CARTEIRA_COBRANCA_SEM_REGISTRO = "SR";

    private final DVGenerator dvGenerator = new DVGenerator();

    public String geraCodigoDeBarrasPara(Boleto boleto) {
        StringBuilder codigoDeBarras = new StringBuilder();
        codigoDeBarras.append(getNumeroFormatado());
        codigoDeBarras.append(String.valueOf(boleto.getCodEspecieMoeda()));

        codigoDeBarras.append(boleto.getFatorVencimento());
        codigoDeBarras.append(boleto.getValorFormatado());

        Emissor emissor = boleto.getEmissor();

        codigoDeBarras.append(emissor.getCarteira());
        codigoDeBarras.append(getSomenteNossoNumeroDoEmissorFormatado(emissor));

        codigoDeBarras.append(emissor.getAgenciaFormatado());
        codigoDeBarras.append(getCodOperacaoFormatado(emissor));
        codigoDeBarras.append(getCodFornecidoPelaAgenciaFormatado(emissor));

        // Digito Verificador da posicao 4 sera inserido aqui.
        codigoDeBarras.insert(4, this.dvGenerator.geraDVMod11(codigoDeBarras.toString()));

        String result = codigoDeBarras.toString();

        if (result.length() != 44) {
            throw new CriacaoBoletoException(
                    "Erro na geração do código de barras. Número de digitos diferente de 44. Verifique todos os dados."
                            + result.length());
        }

        return result;
    }

    public String getCarteiraDoEmissorFormatado(Emissor emissor) {
        return String.format("%02d", emissor.getCarteira());
    }

    public String getContaCorrenteDoEmissorFormatado(Emissor emissor) {
        return String.format("%05d", emissor.getContaCorrente());
    }

    String getCodFornecidoPelaAgenciaFormatado(Emissor emissor) {
        return String.format("%08d", emissor.getCodFornecidoPelaAgencia());
    }

    String getCodOperacaoFormatado(Emissor emissor) {
        return String.format("%03d", emissor.getCodOperacao());
    }

    public URL getImage() {
        return getClass().getResource(
                String.format("/br/com/caelum/stella/boleto/img/%s.png",
                        getNumeroFormatado()));
    }

    public String getSomenteNossoNumeroDoEmissorFormatado(Emissor emissor) {
        int length = 10 - (emissor.getCarteira() / 10);
        return String.format("%0" + (length - 1) + "d", emissor
                .getNossoNumero());
    }

		public String getNossoNumeroDoEmissorFormatado(Emissor emissor) {
			int carteira = emissor.getCarteira();
			String nossoNumero = getSomenteNossoNumeroDoEmissorFormatado(emissor);
			char digitoNossoNumero = emissor.getDvNossoNumero();

			return String.format("%1$01d.%2$s-%3$c", carteira, nossoNumero, digitoNossoNumero);
		}

    public String getNumeroFormatado() {
        return NUMERO_CAIXA;
    }

		public String getNumeroComDigitoFormatado() {
				return String.format("%s-%s", NUMERO_CAIXA, DIGITO_NUMERO_CAIXA);
		}

		public String getLegendaCarteiraDoEmissor(Emissor emissor) {
				if (emissor.getCarteira() == Integer.valueOf(CARTEIRA_COBRANCA_RAPIDA)) {
						return LEGENDA_CARTEIRA_COBRANCA_RAPIDA;
				} else if (emissor.getCarteira() == Integer.valueOf(CARTEIRA_COBRANCA_SEM_REGISTRO)) {
						return LEGENDA_CARTEIRA_COBRANCA_SEM_REGISTRO;
				} else {
						return getCarteiraDoEmissorFormatado(emissor);
				}
		}

		public String getAgenciaCodigoCedenteFormatado(Emissor emissor) {
				Integer agencia = Integer.parseInt(emissor.getAgenciaFormatado().substring(0, 4));
				Integer codigoOperacao = Integer.parseInt(getCodOperacaoFormatado(emissor));
				Integer codigoFornecidoPelaAgencia = Integer.parseInt(getCodFornecidoPelaAgenciaFormatado(emissor));
				Integer digitoCodigoFornecidoPelaAgencia = Integer.parseInt(String.valueOf(emissor.getDvCodFornecidoPelaAgencia()));

				return String.format("%04d.%03d.%08d-%01d", agencia, codigoOperacao, codigoFornecidoPelaAgencia, digitoCodigoFornecidoPelaAgencia );
		}

}
