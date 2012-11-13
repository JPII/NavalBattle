package com.jpii.navalbattle.renderer;

/**
 * Chunks state is the state at which the ChunkRender multithread runs.
 * @author MKirkby
 *
 */
public enum ChunkState {
	STATE_RENDER, STATE_GENERATE, STATE_THROTTLE
}