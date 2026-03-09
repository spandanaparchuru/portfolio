class Incident < ApplicationRecord
  validates :title, presence: true
  validates :status, inclusion: { in: %w[Open InProgress Resolved] }
  after_initialize do
    self.status ||= "Open" if new_record?
  end
end
