/*
 * Copyright 2021 WaterdogTEAM
 * Licensed under the GNU General Public License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.waterdog.waterdogpe.event.defaults;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import dev.waterdog.waterdogpe.event.CancellableEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import lombok.Getter;

public class DownstreamPacketReceiveEvent extends PlayerEvent implements CancellableEvent {
	@Getter
	private final BedrockPacket packet;

	public DownstreamPacketReceiveEvent(BedrockPacket packet, ProxiedPlayer player) {
		super(player);
		this.packet = packet;
	}
}
