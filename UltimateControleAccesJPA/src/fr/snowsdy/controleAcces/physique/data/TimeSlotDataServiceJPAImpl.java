package fr.snowsdy.controleAcces.physique.data;

import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.TimeSlot;

/**
 *
 * @author saturne
 */
class TimeSlotDataServiceJPAImpl extends AbstracCrudServiceJPA<TimeSlot> implements TimeSlotDataService {

    public TimeSlotDataServiceJPAImpl(String PU) {
        super(PU);
    }

}
