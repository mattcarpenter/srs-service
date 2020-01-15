package net.mattcarpenter.benkyou.srsservice.service;

import com.google.common.collect.Lists;
import net.mattcarpenter.benkyou.srsservice.dao.LayoutDao;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutFieldEntity;
import net.mattcarpenter.benkyou.srsservice.mapper.ModelToEntityMapper;
import net.mattcarpenter.benkyou.srsservice.model.LayoutFieldModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LayoutService {

    private LayoutDao layoutDao;

    public LayoutService(LayoutDao layoutDao) {
        this.layoutDao = layoutDao;
    }

    public LayoutEntity createLayout(String name, String frontHtml, String backHtml, String frontCss, String backCss,
                                     List<LayoutFieldModel> fields) {
        LayoutEntity layout = new LayoutEntity();

        layout.setFrontHtml(frontHtml);
        layout.setBackHtml(backHtml);
        layout.setFrontCss(frontCss);
        layout.setBackCss(backCss);
        layout.setName(name);
        fields.forEach(field -> layout.addField(ModelToEntityMapper.mapToLayoutFieldEntity(field)));

        layoutDao.save(layout);

        return layout;
    }

    public LayoutEntity getLayout(UUID id) {
        return layoutDao.findById(id).orElseThrow();
    }

    public LayoutEntity createFieldOnLayout(UUID layoutId, String fieldName) {
        LayoutEntity layout = getLayout(layoutId);
        LayoutFieldEntity field = new LayoutFieldEntity();
        field.setName(fieldName);
        layout.addField(field);
        layoutDao.save(layout);
        return layout;
    }

    public LayoutEntity deleteField(UUID layoutId, String fieldName) {
        LayoutEntity layout = getLayout(layoutId);

        Optional<LayoutFieldEntity> fieldToRemove = layout.getFields().stream()
                .filter(field -> fieldName.equals(field.getName()))
                .findFirst();

        if (fieldToRemove.isPresent()) {
            layout.removeField(fieldToRemove.get());
        } else {
            throw new RuntimeException("Field not found on the provided layout.");
        }

        layoutDao.save(layout);
        return layout;
    }

    public void deleteLayout(UUID id) {
        layoutDao.deleteById(id);
    }

    public List<LayoutEntity> getAllLayouts() {
        return Lists.newArrayList(layoutDao.findAll());
    }
}
