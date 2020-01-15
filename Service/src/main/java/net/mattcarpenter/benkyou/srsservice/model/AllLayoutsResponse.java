package net.mattcarpenter.benkyou.srsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mattcarpenter.benkyou.srsservice.entity.LayoutEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllLayoutsResponse {
    private List<LayoutEntity> layouts;
}
