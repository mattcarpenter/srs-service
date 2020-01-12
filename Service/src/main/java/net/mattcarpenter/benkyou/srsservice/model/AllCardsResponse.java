package net.mattcarpenter.benkyou.srsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mattcarpenter.benkyou.srsservice.entity.CardEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllCardsResponse {
    List<CardEntity> cards;
}
