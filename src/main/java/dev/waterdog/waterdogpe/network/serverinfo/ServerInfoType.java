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

package dev.waterdog.waterdogpe.network.serverinfo;

import com.google.common.base.Preconditions;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ServerInfoType implements Comparable<ServerInfoType> {

    public static final ServerInfoType BEDROCK = new ServerInfoType("bedrock");


    private static final Map<String, ServerInfoType> types = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
    private final String name;

    public ServerInfoType(String name) {
        this.name = name;
    }

    public static ServerInfoType fromString(String string) {
        Preconditions.checkNotNull(string, "ServerInfoType name can not be null");
        Preconditions.checkArgument(!string.isEmpty(), "ServerInfoType name can not be empty");

        ServerInfoType serverInfoType = types.get(string);
        if (serverInfoType == null) {
            types.put(string, serverInfoType = new ServerInfoType(string));
        }
        return serverInfoType;
    }

    public static ServerInfoType getOrBedrock(String string) {
        if (string == null || string.isEmpty()) {
            return BEDROCK;
        }
        return fromString(string);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(ServerInfoType serverInfoType) {
        return this.name.compareTo(serverInfoType.getName());
    }
}
