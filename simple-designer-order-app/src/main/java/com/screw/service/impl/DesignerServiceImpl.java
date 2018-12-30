package com.screw.service.impl;

import com.screw.BusinessException;
import com.screw.domain.designer.Designer;
import com.screw.domain.designer.DesignerRepository;
import com.screw.service.AppExceptionMessage;
import com.screw.service.DesignerService;
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
            BusinessException.throwException(AppExceptionMessage.DESIGNER_NOT_EXIST_CODE, AppExceptionMessage.DESIGNER_NOT_EXIST, designerId);
        }

        designer.enable();

        designerRepository.update(designer);
    }

    @Override
    public void disable(int designerId) {
        Designer designer = designerRepository.selectByKey(designerId);
        if (designer == null) {
            BusinessException.throwException(AppExceptionMessage.DESIGNER_NOT_EXIST_CODE, AppExceptionMessage.DESIGNER_NOT_EXIST, designerId);
        }

        designer.disable();

        designerRepository.update(designer);
    }

    @Override
    public void changePrice(int designerId, float priceByDay) {
        Designer designer = designerRepository.selectByKey(designerId);
        if (designer == null) {
            BusinessException.throwException(AppExceptionMessage.DESIGNER_NOT_EXIST_CODE, AppExceptionMessage.DESIGNER_NOT_EXIST, designerId);
        }

        designer.changePrice(priceByDay);

        designerRepository.update(designer);
    }
}
