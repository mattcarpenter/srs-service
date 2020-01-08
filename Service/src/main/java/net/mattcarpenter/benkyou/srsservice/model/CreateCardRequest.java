package net.mattcarpenter.benkyou.srsservice.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCardRequest {
    private UUID frontFieldId;
    private UUID backFieldId;
    private UUID itemId;
}
