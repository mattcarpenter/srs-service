package net.mattcarpenter.benkyou.srsservice.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AddCardsToDeckRequest {
    List<UUID> cardIds;
}
