package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.local

import androidx.room.InvalidationTracker

class AppDatabaseImpl : AppDatabase() {
    override fun transactionDao(): TransactionDao {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }
}