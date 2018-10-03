package com.screw.service;

import com.screw.DesignerService;
import com.screw.domain.designer.Designer;
import com.screw.domain.designer.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DesignerServiceImpl implements DesignerService {
    @Autowired
    private DesignerRepository designerRepository;

    @Override
    public void enable(int designerId) {
        Designer designer = designerRepository.selectByKey(designerId);
        if (designer == null) {
            return;
        }

        designer.enable();

        designerRepository.update(designer);
    }

    @Override
    public void disable(int designerId) {
        Designer designer = designerRepository.selectByKey(designerId);
        if (designer == null) {
            return;
        }

        designer.disable();

        designerRepository.update(designer);
    }

    @Override
    public void changePrice(int designerId, float priceByDay) {
        Designer designer = designerRepository.selectByKey(designerId);
        if (designer == null) {
            return;
        }

        designer.changePrice(priceByDay);

        designerRepository.update(designer);
    }
}
