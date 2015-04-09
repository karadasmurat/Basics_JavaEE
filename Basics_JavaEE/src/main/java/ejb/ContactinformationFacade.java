/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.contactinfo.ContactInformation;

/**
 *
 * @author 002840
 */
@Stateless
public class ContactinformationFacade extends AbstractFacade<ContactInformation> {

    @PersistenceContext(unitName = "Basics_JavaEE_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactinformationFacade() {
        super(ContactInformation.class);
    }
    
}
