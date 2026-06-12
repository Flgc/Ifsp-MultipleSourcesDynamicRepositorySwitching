package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.firebase

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import kotlinx.coroutines.tasks.await
import java.util.Date
import com.google.firebase.firestore.FirebaseFirestore

class TransactionFirestoreDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("transactions")

    suspend fun getTransactions(): List<Transaction> {
        val snapshot = collection.get().await()
        return snapshot.documents.mapNotNull { doc ->
            val id = doc.id
            val description = doc.getString("description") ?: return@mapNotNull null
            val amount = doc.getDouble("amount") ?: return@mapNotNull null
            val timestamp = doc.getLong("timestamp") ?: return@mapNotNull null
            Transaction(id, description, amount, Date(timestamp))
        }
    }
}