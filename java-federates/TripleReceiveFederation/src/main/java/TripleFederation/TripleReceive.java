package TripleFederation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import c2w.hla.InteractionRoot;
import c2w.hla.ObjectRoot;

/**
 * The federate federate for the federation federation designed in WebGME.
 *
 * This federate MUST join the federation before the federation manager starts the experiment.
 * This means that, when the federate joins, the federation logical time must be 0 and both
 * the readyToPopulate and the readyToRun synchronization points must be defined.
 */
public class TripleReceive extends TripleReceiveBase {
    
	private final static Logger log = LogManager.getLogger(TripleReceive.class);

	public TripleReceive(String[] args) throws Exception {
		super(args);
	}

	private void execute() throws Exception {
		log.info("executing==>");
		double logicalTime = 0;
		InteractionRoot interaction = null;
		ObjectReflector reflector = null;

        ////////////////////////////////////////
        // perform basic initialization below //
        ////////////////////////////////////////

		AdvanceTimeRequest atr = new AdvanceTimeRequest(logicalTime);
		putAdvanceTimeRequest(atr);

		readyToPopulate();
        //////////////////////////////////////////////////////////////////
		// perform initialization that depends on other federates below //
        //////////////////////////////////////////////////////////////////
		readyToRun();

		startAdvanceTimeThread();

		// executes until the federate terminates
		while (true) {
            /////////////////////////////////////////////////////////
            // update step size for logical time progression below //
            /////////////////////////////////////////////////////////
			logicalTime += 1.0;

			atr.requestSyncStart();

            ///////////////////////////////////////////////////////////////////////
			// send interactions that must be sent every logical time step below //
            ///////////////////////////////////////////////////////////////////////
			while ((interaction = getNextInteractionNoWait()) != null) {
				
				 
					if (interaction instanceof Int1) {
						handleInteractionClass((Int1)interaction);
					
				} 
					else if (interaction instanceof Int3) {
						handleInteractionClass((Int3)interaction);
					
				} 
					else if (interaction instanceof Int2) {
						handleInteractionClass((Int2)interaction);
					
				}			
			}

			while ((reflector = getNextObjectReflectorNoWait()) != null) {
				reflector.reflect();
				ObjectRoot object = reflector.getObjectRoot();
				
							
			}

			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// DO NOT MODIFY THE FOLLOWING LINES
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			AdvanceTimeRequest newATR = new AdvanceTimeRequest(logicalTime);
			putAdvanceTimeRequest(newATR);
			atr.requestSyncEnd();
			atr = newATR;
		}
	}
	

	private void handleInteractionClass(Int1 interaction) {
        /////////////////////////////////////////////////////////////////////
		// implement how to handle reception of the interaction Int1 below //
        /////////////////////////////////////////////////////////////////////
	}

	private void handleInteractionClass(Int3 interaction) {
        /////////////////////////////////////////////////////////////////////
		// implement how to handle reception of the interaction Int3 below //
        /////////////////////////////////////////////////////////////////////
	}

	private void handleInteractionClass(Int2 interaction) {
        /////////////////////////////////////////////////////////////////////
		// implement how to handle reception of the interaction Int2 below //
        /////////////////////////////////////////////////////////////////////
	}	
		

	public static void main(String[] args) {
		try {
			TripleReceive instance = new TripleReceive(args);
			log.info("start==>");
			instance.execute();
		} catch (Exception e) {
			log.error("Exception caught: " + e);
		}
	}	
}