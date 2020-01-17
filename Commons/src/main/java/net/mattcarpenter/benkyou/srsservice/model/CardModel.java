package net.mattcarpenter.benkyou.srsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardModel {
    private UUID id;
    private UUID layoutId;
    private List<CardFieldModel> fields;
}
