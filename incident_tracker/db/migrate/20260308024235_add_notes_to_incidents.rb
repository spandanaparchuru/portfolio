class AddNotesToIncidents < ActiveRecord::Migration[8.1]
  def change
    add_column :incidents, :notes, :text
  end
end
