/*
 * Copyright 2019-2021 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/dev/LICENSE
 */

package net.mamoe.mirai.mock.contact

import me.him188.kotlin.jvm.blocking.bridge.JvmBlockingBridge
import net.mamoe.mirai.contact.Stranger
import net.mamoe.mirai.mock.MockBotDSL

@JvmBlockingBridge
public interface MockStranger : Stranger, MockContact, MockUser {
    public interface MockApi {
        val contact: MockStranger
        var nick: String
        var remark: String
    }

    /**
     * 获取直接修改字段内容的 API, 通过该 API 修改的值都不会触发广播
     */
    public val mockApi: MockApi

    /**
     * 广播陌生人主动解除与 [bot] 的关系的事件
     *
     * 即使该函数体实现为 [delete], 也请使用该方法广播 **bot 被陌生人删除**，
     * 以确保不会受到未来的事件架构变更带来的影响
     */
    @MockBotDSL
    public suspend fun broadcastStrangerDeleteEvent() {
        delete()
    }
}
