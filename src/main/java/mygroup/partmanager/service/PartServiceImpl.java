package mygroup.partmanager.service;

import mygroup.partmanager.dao.PartDAO;
import mygroup.partmanager.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private PartDAO partDAO;

    @Autowired
    public void setPartDAO(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    @Override
    @Transactional
    public List<Part> allParts(int page, Boolean required, String search) {
        return partDAO.allParts(page, required, search);
    }

    @Override
    @Transactional
    public int partsCount(Boolean required, String search) {
        return partDAO.partsCount(required, search);
    }

    @Override
    @Transactional
    public int allPartsCount() {
        return partDAO.allPartsCount();
    }

    @Override
    @Transactional
    public int computersCount() {
        return partDAO.computersCount();
    }

    @Override
    @Transactional
    public void add(Part part) {
        partDAO.add(part);
    }

    @Override
    @Transactional
    public void delete(Part part) {
        partDAO.delete(part);
    }

    @Override
    @Transactional
    public void edit(Part part) {
        partDAO.edit(part);
    }

    @Override
    @Transactional
    public Part getById(int id) {
        return partDAO.getById(id);
    }

    @Override
    @Transactional
    public boolean checkTitle(String title) {
        return partDAO.checkTitle(title);
    }
}
