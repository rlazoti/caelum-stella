package br.com.caelum.stella.boleto;

import java.net.URL;

/**
 * Interface que define métodos específicos ao funcionamento de cada banco para
 * gerar o código de barras e a linha digitável.<br/>
 * <br/>
 *
 * Infelizmente cada banco funciona de uma maneira diferente para gerar esses
 * valores, e as vezes até mudam de funcionamento.
 *
 * Documentação que pode ser consultada:<br/>
 * <br/>
 *
 * http://www.macoratti.net/boleto.htm<br/>
 * http://pt.wikipedia.org/wiki/Boleto_bancário<br/>
 * <br/>
 *
 * Para testes rapidos: http://evandro.net/codigo_barras.html<br/>
 * <br/>
 *
 * Apesar de possuirmos diversos unit tests, sempre é bom ter precaução com
 * valores e testar alguns boletos, em especial se valores serão altos.
 *
 * @author Paulo Silveira
 * @author Cauê Guerra
 *
 */
public interface Banco {

    /**
     * Retorna o número desse banco, formatado com 3 dígitos
     *
     * @return
     */
    String getNumeroFormatado();

    /**
     * Retorna o número desse banco (3 dígitos) com seu dígito, no formato ###-#
     * Caso não exista dígito será retornado o resultado do método get getNumeroFormatado
     *
     * @return
     */
    String getNumeroComDigitoFormatado();

    /**
     * Pega a URL com a imagem de um banco
     *
     * @return
     */
    URL getImage();

    /**
     * Gera o código de barras para determinado boleto
     */
    String geraCodigoDeBarrasPara(Boleto boleto);

    String getContaCorrenteDoEmissorFormatado(Emissor emissor);

    String getCarteiraDoEmissorFormatado(Emissor emissor);

    /**
     * Retorna uma legenda pre-existente para um determinado tipo de carteira,
     * caso nao exista nenhuma legenda sera retornado o resultado do método getCarteiraDoEmissorFormatado
     * @param emissor
     *
     * @return
     */
    String getLegendaCarteiraDoEmissor(Emissor emissor);

    /**
     * Retorna somente o nosso número do emissor sem a carteira e sem o digito do nosso numero
     * @param emissor
     *
     * @return
     */
    String getSomenteNossoNumeroDoEmissorFormatado(Emissor emissor);

    /**
     * Retorna o nosso número do emissor com a carteira e com o digito do nosso numero
     * Caso não existe uma formatação definida será retornado o resultado do método getNossoNumeroDoEmissor
     * @param emissor
     *
     * @return
     */
    String getNossoNumeroDoEmissorFormatado(Emissor emissor);

    /**
     * Retorna a agencia e o codigo do cedente no formato AAAA/CCCCCCCC-D
     * Onde A = Agencia, C = Código do Cedente fornecido pela agência e D = Dígito do Cedente fornecido pela agência
     * @param emissor
     *
     * @return
     */
    String getAgenciaCodigoCedenteFormatado(Emissor emissor);

}
