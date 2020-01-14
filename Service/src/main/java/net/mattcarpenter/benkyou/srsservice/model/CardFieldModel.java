package net.mattcarpenter.benkyou.srsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardFieldModel {
    private String name;
    private String value;
}
