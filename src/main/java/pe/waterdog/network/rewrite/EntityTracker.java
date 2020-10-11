/**
 * Copyright 2020 WaterdogTEAM
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.waterdog.network.rewrite;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.*;
import pe.waterdog.player.ProxiedPlayer;

import java.util.List;

public class EntityTracker implements BedrockPacketHandler {

    private final ProxiedPlayer player;

    public EntityTracker(ProxiedPlayer player) {
        this.player = player;
    }

    public boolean trackEntity(BedrockPacket packet) {
        return packet.handle(this);
    }

    @Override
    public boolean handle(AddPlayerPacket packet) {
        this.player.getEntities().add(packet.getRuntimeEntityId());
        return false;
    }

    @Override
    public boolean handle(AddEntityPacket packet) {
        this.player.getEntities().add(packet.getRuntimeEntityId());
        return false;
    }

    @Override
    public boolean handle(AddItemEntityPacket packet) {
        this.player.getEntities().add(packet.getRuntimeEntityId());
        return false;
    }

    @Override
    public boolean handle(AddPaintingPacket packet) {
        this.player.getEntities().add(packet.getRuntimeEntityId());
        return false;
    }

    @Override
    public boolean handle(RemoveEntityPacket packet) {
        this.player.getEntities().remove(packet.getUniqueEntityId());
        return false;
    }

    @Override
    public boolean handle(PlayerListPacket packet) {
        List<PlayerListPacket.Entry> entries = packet.getEntries();
        for (PlayerListPacket.Entry entry : entries) {
            if (packet.getAction() == PlayerListPacket.Action.ADD) {
                this.player.getPlayers().add(entry.getUuid());
            } else if (packet.getAction() == PlayerListPacket.Action.REMOVE) {
                this.player.getPlayers().remove(entry.getUuid());
            }
        }
        return false;
    }
}