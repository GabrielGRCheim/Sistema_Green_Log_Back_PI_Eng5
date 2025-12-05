package com.senai.demo.models.padraoprojeto.factory;

import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.padraoprojeto.decorator.*;

import java.util.List;

public class ResiduoFactory {

    public static Residuos criar(List<TipoResiduo> tipos) {

        Residuos r = null;

        for (TipoResiduo tipo : tipos) {

            switch (tipo) {
                case PLASTICO:
                    r = new ResiduoPlastico(r);
                    break;

                case VIDRO:
                    r = new ResiduoVidro(r);
                    break;

                case METAL:
                    r = new ResiduoMetal(r);
                    break;

                case PAPEL:
                    r = new ResiduoPapel(r);
                    break;

                case ORGANICO:
                    r = new ResiduoOrganico(r);
                    break;
            }
        }

        return r;
    }
}
