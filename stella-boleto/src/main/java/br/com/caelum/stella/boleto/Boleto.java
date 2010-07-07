package br.com.caelum.stella.boleto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Bean que representa os dados de um boleto.
 *
 * @author Paulo Silveira
 * @author Caue Guerra
 *
 */
public class Boleto {

    private BigDecimal valorBoleto;

    private BigDecimal qtdMoeda;
    private BigDecimal valorMoeda;
    private String especieMoeda;
    private int codEspecieMoeda;

    private String especieDocumento;
    private String noDocumento;
    private boolean aceite;

    private Banco banco;
    private Datas datas;
    private Sacado sacado;
    private Emissor emissor;
    private List<String> instrucoes = Collections.emptyList();
    private List<String> descricoes = Collections.emptyList();
    private List<String> locaisDePagamento = Collections.emptyList();

    private Boleto() {
    }

    /**
     * Cria um novo boleto com valores padrão.
     *
     */
    public static Boleto newBoleto() {
        return new Boleto().withEspecieMoeda("R$").withCodEspecieMoeda(9)
                .withAceite(false).withEspecieDocumento("DV");
    }

    /**
     * Devolve o aceite do boleto Valor padrão: 'N'
     *
     * @return
     */
    public boolean getAceite() {
        return this.aceite;
    }

    /**
     * Associa o aceite ao boleto. Valor padrão: 'N'
     *
     * @param aceite
     */
    public Boleto withAceite(boolean aceite) {
        this.aceite = aceite;
        return this;
    }

    /**
     * Devolve as datas do boleto
     *
     * @return
     */
    public Datas getDatas() {
        return this.datas;
    }

    /**
     * Associa datas ao boleto
     *
     * @param datas
     *
     */
    public Boleto withDatas(Datas datas) {
        this.datas = datas;
        return this;
    }

    /**
     * Devolve a espécie do documento do boleto. Valor padrão: "DV"
     *
     * @return
     */
    public String getEspecieDocumento() {
        return this.especieDocumento;
    }

    /**
     * Associa a espécie do documento ao boleto. Valor padrão: "DV"
     *
     * @param especieDocumento
     */
    public Boleto withEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
        return this;
    }

    /**
     * Devolve o número do documento. Código informado pelo banco.
     *
     * @return
     */
    public String getNoDocumento() {
        return this.noDocumento;
    }

    /**
     * Associa o número do documento ao boleto Código informado pelo banco.
     *
     * @param noDocumento
     */
    public Boleto withNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
        return this;
    }

    /**
     * Devolve a quantidade da moeda
     *
     * @return
     */
    public BigDecimal getQtdMoeda() {
        return this.qtdMoeda;
    }

    /**
     * Associa a quantidade de moeda ao boleto
     *
     * @param qtdMoeda
     * @return
     */
    public Boleto withQtdMoeda(BigDecimal qtdMoeda) {
        this.qtdMoeda = qtdMoeda;
        return this;
    }

    /**
     * Devolve o valor desse boleto
     */
    public BigDecimal getValorBoleto() {
        return this.valorBoleto;
    }

    /**
     * Associa um valor ao boleto, convertendo a String para BigDecimal
     * utilizando o Locale da JVM
     *
     * @param valorBoleto
     * @return
     */
    public Boleto withValorBoleto(String valorBoleto) {
        this.valorBoleto = new BigDecimal(valorBoleto);
        return this;

    }

    public Boleto withValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
        return this;
    }

    public Boleto withValorBoleto(double valorBoleto) {
        this.valorBoleto = new BigDecimal(valorBoleto);
        return this;
    }

    /**
     * Devolve a espécie da moeda. Valor padrão: "R$"
     *
     * @return
     */
    public String getEspecieMoeda() {
        return this.especieMoeda;
    }

    /**
     * Associa uma espécie de moeda ao boleto. Valor padrão: "R$"
     *
     * @param especieMoeda
     * @return
     */
    private Boleto withEspecieMoeda(String especieMoeda) {
        this.especieMoeda = especieMoeda;
        return this;
    }

    /**
     * Devolve o código da espécie da moeda. Valor padrão: '9' (para R$)
     *
     * @return
     */
    public int getCodEspecieMoeda() {
        return this.codEspecieMoeda;
    }

    /**
     * Associa um código da espécie da moeda ao boleto. Valor padrão: '9' (para
     * R$)
     *
     * @param codEspecieMoeda
     * @return
     */
    private Boleto withCodEspecieMoeda(int codEspecieMoeda) {
        this.codEspecieMoeda = codEspecieMoeda;
        return this;
    }

    /**
     * Devolve o valor da moeda, format
     *
     * @return
     */
    public BigDecimal getValorMoeda() {
        return this.valorMoeda;
    }

    /**
     * Associa um valor de moeda ao boleto
     *
     */
    public Boleto withValorMoeda(BigDecimal valorMoeda) {
        this.valorMoeda = valorMoeda;
        return this;
    }

    /**
     * Devolve o banco do boleto
     *
     * @return
     */
    public Banco getBanco() {
        return this.banco;
    }

    /**
     * Associa um banco ao boleto
     *
     * @param banco
     * @return
     */
    public Boleto withBanco(Banco banco) {
        this.banco = banco;
        return this;
    }

    /**
     * Devolve o sacado do banco
     *
     * @return
     */
    public Sacado getSacado() {
        return this.sacado;
    }

    /**
     * Associa um sacado ao banco
     *
     * @param sacado
     * @return
     */
    public Boleto withSacado(Sacado sacado) {
        this.sacado = sacado;
        return this;
    }

    /**
     * Devolve o emissor do boleto
     *
     * @return
     */
    public Emissor getEmissor() {
        return this.emissor;
    }

    /**
     * Associa um sacado ao boleto
     *
     * @param emissor
     * @return
     */
    public Boleto withEmissor(Emissor emissor) {
        this.emissor = emissor;
        return this;
    }

    /**
     * Devolve as instruções do boleto
     *
     * @return
     */
    public List<String> getInstrucoes() {
        return this.instrucoes;
    }

    /**
     * Associa instruções ao boleto
     *
     * @param instrucoes
     * @return
     */
    public Boleto withInstrucoes(String... instrucoes) {
        if (instrucoes.length > 5) {
            throw new IllegalArgumentException(
                    "maximo de 5 instrucoes permitidas");
        }
        this.instrucoes = Arrays.asList(instrucoes);
        return this;
    }

    /**
     * Devolve as descrições do boleto. Note que esse campo não aparece no
     * boleto gerado em PNG
     *
     * @return
     */
    public List<String> getDescricoes() {
        return this.descricoes;
    }

    /**
     * Associa as descrições ao boleto. Note que esse campo não aparece no
     * boleto gerado em PNG
     *
     * @return
     */
    public Boleto withDescricoes(String... descricoes) {
        if (descricoes.length > 5) {
            throw new IllegalArgumentException(
                    "maximo de 5 descricoes permitidas");
        }
        this.descricoes = Arrays.asList(descricoes);
        return this;
    }

    /**
     * Devolve os locais de pagamento do boleto
     *
     * @return
     */
    public List<String> getLocaisDePagamento() {
        return this.locaisDePagamento;
    }

    /**
     * Associa locais de pagamento ao boleto
     *
     * @param locaisDePagamento
     * @return
     */
    public Boleto withLocaisDePagamento(String... locaisDePagamento) {
        if (locaisDePagamento.length > 2) {
            throw new IllegalArgumentException(
                    "maximo de 2 locais de pagamento permitidos");
        }
        this.locaisDePagamento = Arrays.asList(locaisDePagamento);
        return this;
    }

    /**
     * Devolve o fator de vencimento do boleto. Utilizado para geração do código
     * de barras e da linha digitável
     *
     * @return
     */
    public String getFatorVencimento() {
        Calendar dataBase = Calendar.getInstance();
        dataBase.set(Calendar.DAY_OF_MONTH, 7);
        dataBase.set(Calendar.MONTH, 10 - 1);
        dataBase.set(Calendar.YEAR, 1997);
        dataBase.set(Calendar.HOUR_OF_DAY, 0);
        dataBase.set(Calendar.MINUTE, 0);
        dataBase.set(Calendar.SECOND, 0);
        dataBase.set(Calendar.MILLISECOND, 0);

        Calendar vencimentoSemHoras = Calendar.getInstance();
        vencimentoSemHoras.set(Calendar.DAY_OF_MONTH, this.datas
                .getVencimento().get(Calendar.DAY_OF_MONTH));
        vencimentoSemHoras.set(Calendar.MONTH, this.datas.getVencimento().get(
                Calendar.MONTH));
        vencimentoSemHoras.set(Calendar.YEAR, this.datas.getVencimento().get(
                Calendar.YEAR));
        vencimentoSemHoras.set(Calendar.HOUR_OF_DAY, 0);
        vencimentoSemHoras.set(Calendar.MINUTE, 0);
        vencimentoSemHoras.set(Calendar.SECOND, 0);
        vencimentoSemHoras.set(Calendar.MILLISECOND, 0);

        long diferencasEmMiliSegundos = vencimentoSemHoras.getTimeInMillis()
                - dataBase.getTimeInMillis();
        long diferencasEmDias = diferencasEmMiliSegundos
                / (1000 * 60 * 60 * 24);

        if (diferencasEmDias > 9999) {
            throw new CriacaoBoletoException("Data fora do formato aceito!");
        }

        return String.valueOf((int) diferencasEmDias);
    }

    /**
     * Devolve o valor do boleto formatado (com 10 digitos)
     *
     * @return
     */
    public String getValorFormatado() {
        return String.format("%011.2f", this.valorBoleto).replaceAll("[^0-9]",
                "");
    }

    /**
     * Devolve o número do documento formatado (com 4 digitos) caso ele contenha apenas números,
     * caso tenha outros caracteres será considerado seu tamanho total.
     *
     * @return
     */
    public String getNoDocumentoFormatado() {
    	if (this.noDocumento == null) {
    		return null;
    	}
    	else if (StringUtils.isNumeric(this.noDocumento)) {
    			return getNoDocumentoFormatado(4);
    	}
    	else {
    		return getNoDocumentoFormatado(this.noDocumento.length());
    	}
    }

    /**
     * Devolve o número do documento formatado com a quantidade de dígitos especificado pelo parâmetro tamanho.
     * O método formata tanto conteúdo com apenas números como com outros caracteres.
     * @param tamanho
     * @return
     */
    public String getNoDocumentoFormatado(int tamanho) {
    		String formato = "%" + ((StringUtils.isNumeric(this.noDocumento)) ? "0" + tamanho + "d" : tamanho + "s");
    		return String.format(formato, ((StringUtils.isNumeric(this.noDocumento)) ? Long.parseLong(this.noDocumento) : this.noDocumento));
    }

}
