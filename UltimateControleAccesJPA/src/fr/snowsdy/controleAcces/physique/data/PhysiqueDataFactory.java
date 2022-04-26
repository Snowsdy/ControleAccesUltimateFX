package fr.snowsdy.controleAcces.physique.data;


/**
 *
 * @author fanou
 */
public class PhysiqueDataFactory {
    private static final String PU = "controleAccesPU";

    private PhysiqueDataFactory() {
    }

    private static AttributionDataService attributionDataService = null;
    public static AttributionDataService getAttributionDataService() {
        if (attributionDataService == null) {
            attributionDataService = new AttributionDataServiceJPAImpl(PU);
        }
        return attributionDataService;
    }

    private static SalleDataService salleDataService = null;
    public static SalleDataService getSalleDataService() {
        if (salleDataService == null) {
            salleDataService = new SalleDataServiceJPAImpl(PU);
        }
        return salleDataService;
    }

    private static AutorisationDataService autorisationDataService = null;
    public static AutorisationDataService getAutorisationDataService() {
        if (autorisationDataService == null) {
            autorisationDataService = new AutorisationDataServiceJPAImpl(PU);
        }
        return autorisationDataService;
    }

    private static BadgeDataService badgeDataService = null;
    public static BadgeDataService getBadgeDataService() {
        if (badgeDataService == null) {
            badgeDataService = new BadgeDataServiceJPAImpl(PU);
        }
        return badgeDataService;
    }

    private static EvenementDataService evenementDataService = null;
    public static EvenementDataService getEvenementDataService() {
        if (evenementDataService == null) {
            evenementDataService = new EvenementDataServiceJPAImpl(PU);
        }
        return evenementDataService;
    }

    private static PersonneDataService personneDataService = null;
    public static PersonneDataService getPersonneDataService() {
        if (personneDataService == null) {
            personneDataService = new PersonneDataServiceJPAImpl(PU);
        }
        return personneDataService;
    }

    private static TimeSlotDataService timeSlotDataService = null;
    public static TimeSlotDataService getTimeSlotService() {
        if (timeSlotDataService == null) {
            timeSlotDataService = new TimeSlotDataServiceJPAImpl(PU);
        }
        return timeSlotDataService;
    }

}
