package org.example.service;

import java.util.List;
import org.example.dao.LiteraryFormatDao;
import org.example.lib.Inject;
import org.example.lib.Service;
import org.example.model.LiteraryFormat;

@Service
public class LiteraryFormatServiceImpl implements LiteraryFormatService {
    @Inject
    private LiteraryFormatDao literaryFormatDao;

    @Override
    public List<LiteraryFormat> getAll() {
        return literaryFormatDao.getAll();
    }

    @Override
    public LiteraryFormat create(LiteraryFormat format) {
        return literaryFormatDao.create(format);
    }

    @Override
    public LiteraryFormat get(Long id) {
        return literaryFormatDao.get(id);
    }

    @Override
    public LiteraryFormat update(LiteraryFormat format) {
        return literaryFormatDao.update(format);
    }

    @Override
    public boolean delete(Long id) {
        return literaryFormatDao.delete(id);
    }
}
