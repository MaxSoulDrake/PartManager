package mygroup.partmanager.service;

import mygroup.partmanager.model.Part;

import java.util.List;

public interface PartService {
    List<Part> allParts(int page, Boolean required, String search);
    void add(Part part);
    void delete(Part part);
    void edit(Part part);
    Part getById(int id);

    int partsCount(Boolean required, String search);
    int allPartsCount();
    int computersCount();

    boolean checkTitle(String title);
}
