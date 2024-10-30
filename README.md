# Kitchen-tech-backend
Backend Development of Kitchen Tech

API Endpoints
Endpoint	Método	Controlador	Función
/api/kitchen/v1/services	GET	ServiceController	Obtiene la lista de todos los servicios disponibles en Kitchen Tech.
/api/kitchen/v1/services/{id}	GET	ServiceController	Obtiene los detalles de un servicio específico por su ID.
/api/kitchen/v1/services	POST	ServiceController	Crea un nuevo servicio en el sistema.
/api/kitchen/v1/services/{id}	PUT	ServiceController	Actualiza la información de un servicio existente.
/api/kitchen/v1/services/{id}	DELETE	ServiceController	Elimina un servicio del sistema por su ID.
/api/kitchen/v1/tasks	GET	TaskController	Obtiene la lista de todas las tareas relacionadas con los servicios.
/api/kitchen/v1/tasks/{id}	GET	TaskController	Obtiene los detalles de una tarea específica por su ID.
/api/kitchen/v1/tasks	POST	TaskController	Crea una nueva tarea asociada a un servicio en el sistema.
/api/kitchen/v1/tasks/{id}	PUT	TaskController	Actualiza la información de una tarea existente.
/api/kitchen/v1/tasks/{id}	DELETE	TaskController	Elimina una tarea específica del sistema por su ID.
/api/kitchen/v1/reviews	GET	ReviewController	Obtiene una lista de todas las reseñas de servicios.
/api/kitchen/v1/reviews/{id}	GET	ReviewController	Obtiene los detalles de una reseña específica por su ID.
/api/kitchen/v1/reviews	POST	ReviewController	Crea una nueva reseña para un servicio.
/api/kitchen/v1/contracts	GET	ContractController	Obtiene la lista de todos los contratos registrados en el sistema.
/api/kitchen/v1/contracts/{id}	GET	ContractController	Obtiene los detalles de un contrato específico por su ID.
/api/kitchen/v1/contracts	POST	ContractController	Crea un nuevo contrato en el sistema.
/api/kitchen/v1/contracts/{id}	PUT	ContractController	Actualiza la información de un contrato existente.
/api/kitchen/v1/contracts/{id}	DELETE	ContractController	Elimina un contrato específico del sistema por su ID.
