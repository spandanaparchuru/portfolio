class IncidentsController < ApplicationController
  before_action :set_incident, only: %i[ show edit update destroy ]

  # GET /incidents or /incidents.json
  def index
    if params[:status].present?
      @incidents = Incident.where(status: params[:status])
    else
      @incidents = Incident.all
    end
  end

  # GET /incidents/1 or /incidents/1.json
  def show
  end

  # GET /incidents/new
  def new
    @incident = Incident.new
  end

  # GET /incidents/1/edit
  def edit
  end

  # POST /incidents or /incidents.json
  def create
    @incident = Incident.new(incident_params)

    respond_to do |format|
      if @incident.save
        format.html { redirect_to @incident, notice: "Incident was successfully created." }
        format.json { render :show, status: :created, location: @incident }
        Rails.logger.info "Incident Created: #{ @incident.title }"
      else
        format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @incident.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /incidents/1 or /incidents/1.json
  def update
    respond_to do |format|
      if @incident.update(incident_params)
        format.html { redirect_to @incident, notice: "Incident was successfully updated.", status: :see_other }
        format.json { render :show, status: :ok, location: @incident }
        Rails.logger.info "Incident Updated: #{ @incident.title }"
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @incident.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /incidents/1 or /incidents/1.json
  def destroy
    @incident.destroy!

    respond_to do |format|
      format.html { redirect_to incidents_path, notice: "Incident was successfully destroyed.", status: :see_other }
      format.json { head :no_content }
      Rails.logger.warn "Incident Deleted: #{ @incident.title }"
    end
  end

  def bulk_delete
      Rails.logger.debug "PARAMS RECEIVED: #{params.inspect}"

      if params[:incident_ids].present?
        Incident.where(id: params[:incident_ids]).destroy_all
        redirect_to incidents_path, notice: "Deleted."
      else
        redirect_to incidents_path, alert: "No incidents selected."
      end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_incident
      @incident = Incident.find(params.expect(:id))
    end

    # Only allow a list of trusted parameters through.
    def incident_params
      params.require(:incident).permit(:title, :description, :status, :notes)
    end
end
