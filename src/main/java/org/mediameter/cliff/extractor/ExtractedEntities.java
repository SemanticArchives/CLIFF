package org.mediameter.cliff.extractor;

import java.util.ArrayList;
import java.util.List;

import org.mediameter.cliff.orgs.ResolvedOrganization;
import org.mediameter.cliff.people.ResolvedPerson;

import com.bericotech.clavin.extractor.LocationOccurrence;
import com.bericotech.clavin.gazetteer.CountryCode;
import com.bericotech.clavin.resolver.ResolvedLocation;

/**
 * Simple wrapper around results generated by Stanford's NER
 * @author rahulb
 */
public class ExtractedEntities {

    private List<LocationOccurrence> locations;
    private List<ResolvedLocation> resolvedLocations;

    private List<PersonOccurrence> people;
    private List<ResolvedPerson> resolvedPeople;

    private List<OrganizationOccurrence> organizations;
    private List<ResolvedOrganization> resolvedOrganizations;

    public ExtractedEntities(){
        locations = new ArrayList<LocationOccurrence>();
        resolvedPeople = new ArrayList<ResolvedPerson>();
        people = new ArrayList<PersonOccurrence>();
        resolvedLocations = new ArrayList<ResolvedLocation>();
        organizations = new ArrayList<OrganizationOccurrence>();
        resolvedOrganizations = new ArrayList<ResolvedOrganization>();
    }

    public void addOrganization(OrganizationOccurrence organization) {
        organizations.add(organization);
    }

    public void addPerson(PersonOccurrence person) {
        people.add(person);
    }

    public void addLocation(LocationOccurrence location) {
        locations.add(location);        
    }

    public List<PersonOccurrence> getPeople() {
        return people;
    }

    public List<LocationOccurrence> getLocations() {
        return locations;
    }

    public List<OrganizationOccurrence> getOrganizations() {
        return organizations;
    }

    public void setResolvedLocations(List<ResolvedLocation> locs) {
        resolvedLocations = locs;
    }

    public List<ResolvedLocation> getResolvedLocations() {
        return resolvedLocations;
    }

    public List<ResolvedOrganization> getResolvedOrganizations() {
        return resolvedOrganizations;
    }

    public List<CountryCode> getUniqueCountries(){
        return getUniqueCountries(resolvedLocations);
    }
    
    public static List<CountryCode> getUniqueCountries(List<ResolvedLocation> resolvedLocations){
        List<CountryCode> countries = new ArrayList<CountryCode>();
        for(ResolvedLocation resolvedLocation: resolvedLocations){
            CountryCode country = resolvedLocation.geoname.primaryCountryCode;
            if(country==CountryCode.NULL){  // skip things that aren't in countries (ie. "Asia")
                continue;
            }
            if( !countries.contains(country) ){
                countries.add(country);
            }
        }
        return countries;
    }

    public void setResolvedPeople(List<ResolvedPerson> resolvedPeople) {
        this.resolvedPeople = resolvedPeople;
    }

    public List<ResolvedPerson> getResolvedPeople() {
        return resolvedPeople;
    }

    public void setResolvedOrganizations(List<ResolvedOrganization> resolvedOrganizations) {
        this.resolvedOrganizations = resolvedOrganizations;
    }
    
}
