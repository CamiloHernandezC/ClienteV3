/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.PersonasSucursal;

/**
 * Modelo usado para almacenar la entidad y la observacion de los errores al guardar desde archivo
 * @author Kmilo
 */
public class PersonasSucursalModel extends AbstractMasterDataModel<PersonasSucursal>{
    private PersonasSucursal entity;

    @Override
    public void setEntity(PersonasSucursal entity) {
        this.entity = entity;
    }

    @Override
    public PersonasSucursal getEntity() {
        return entity;
    }
    
}
