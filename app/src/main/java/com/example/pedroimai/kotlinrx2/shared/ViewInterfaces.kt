package com.example.pedroimai.kotlinrx2.shared

interface EmptyStateView {
    fun showEmptyState()
    fun hideEmptyState()
}

interface ErrorStateView {
    fun showErrorState()
    fun hideErrorState()
}

interface LoadingContentView {
    fun showLoading()
    fun hideLoading()
}

interface BindablePresenter {
    fun bind()
    fun unbind()
}