package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<Organization> organizations;

    public OrganizationListSection() {
        organizations = new ArrayList<>();
    }

    public OrganizationListSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationListSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    public void removeExperience(Organization organization) {
        organizations.remove(organization);
    }

    public List<Organization> getOrganizationList() {
        return organizations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization organization : organizations) {
            result.append(organization.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationListSection that = (OrganizationListSection) o;

        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations != null ? organizations.hashCode() : 0;
    }
}
