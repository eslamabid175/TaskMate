package com.example.taskmate.di

import com.example.taskmate.data.database.TaskDatabase
import com.example.taskmate.data.repository.TaskRepository
import com.example.taskmate.ui.viewmodel.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
//5- let’s set up Koin to provide dependencies like the database and repository
val appModule = module {
    // Define your dependencies here
    // e.g., single { Repository(get()) }
    single { TaskDatabase.getDatabase(androidContext()).taskDao()  }
    //single { get<TaskDatabase>().taskDao() }
    single { TaskRepository(get()) }
    viewModel { TaskViewModel(get()) }

}
