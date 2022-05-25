package com.tuyo.tuyofood.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/* 1. Esta classe representa um modelo de representação da API, e não um modelo de domínio:
 *  Uma represetação de um recurso.
 * 2. @JacksonXmlRootElement é uma alternativa à @JsonRootName e
 * @JacksonXmlProperty à @JsonProperty.
 * 3.  A diferença é que as anotações iniciadas com @JacksonXml só afetam
 * a serialização em XML. Já as anotações iniciadas com @Json
 * afetam tanto a serialização JSON como também XML.
 * 4. Esta classe vai empacotar uma lista de kitchens.
 * 5. Data gera os Getters e Setters e também os construtores para propriedades obrigatórias.
 * 6. Para dizer que uma propriedade é obrigatória, anotamos com @NonNull,
 * que gera um construtor que recebe Kitchen e atribui a variável de instância kitchens.
 * E sem o @NonNull, ele não geraria o construtor kitchens.
 */

@JsonRootName("kitchens")
//@JacksonXmlRootElement(localName = "kitchens")
@Data
public class KitchensRepresentationModel {

    @JsonProperty("kitchens")
//	@JacksonXmlProperty(localName = "kitchens")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Kitchen> kitchens;

}
