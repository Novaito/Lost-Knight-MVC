package up.l3info.LostKnight.mvc;


/// Generic Controller for the triple {Model-View-Controller}.
/// Every Controller can create some sub-controllers.
///
/// @author X. Skapin
/// @version 1.0.0
public abstract class Controller<M extends Model, V extends View > {
	
		/// Related [Model]
		protected final M model;

		/// Related [View]
		protected final V view;

		/// Custom constructor
		///
		/// @param p_model Related [Model]. Should not be null.
		/// @param p_view Related [View]. Should not be null.
		public Controller(M p_model, V p_view) {
				this.model = p_model;
				this.view = p_view;
		}

		/// {@return the related [Model]}
		public M getModel() {
				return this.model;
		}
		
		/// {@return the related [View]}
		public V getView() {
				return this.view;
		}

		/// Initialize this instance's contents. This method should be called immediately after calling the constructor.		
		public abstract void init();
}
