Rails.application.routes.draw do
  resources :incidents do
    collection do
      post :bulk_delete
    end
  end

  get "up" => "rails/health#show", as: :rails_health_check
end
