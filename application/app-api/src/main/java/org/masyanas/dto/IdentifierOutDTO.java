package org.masyanas.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "identifier")
public class IdentifierOutDTO implements IIdentifierOutDTO
{

    private static final long serialVersionUID = 1L;


    private Long id;


    @Override
    public Long getId()
    {
        return null;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }
}
