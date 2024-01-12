package com.bottlerocketstudios.launchpad.compose.navigation.utils

/**
 * Information about the posture of the device
 */
sealed interface DevicePosture {
    object NormalPosture : DevicePosture

    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}

data class Rect(
    val bottom: Int,
    val left: Int,
    val right: Int,
    val top: Int
)

interface DisplayFeature {
    val bounds: Rect
}

interface FoldingFeature : DisplayFeature {

    /**
     * Represents how the hinge might occlude content.
     */
    class OcclusionType private constructor(private val description: String) {

        override fun toString(): String {
            return description
        }
    }

    /**
     * Represents the axis for which the [FoldingFeature] runs parallel to.
     */
    class Orientation private constructor(private val description: String) {

        override fun toString(): String {
            return description
        }

        companion object {

            /**
             * The height of the [FoldingFeature] is greater than or equal to the width.
             */
            val VERTICAL: Orientation = Orientation("VERTICAL")

            /**
             * The width of the [FoldingFeature] is greater than the height.
             */
            val HORIZONTAL: Orientation = Orientation("HORIZONTAL")
        }
    }

    /**
     * Represents the [State] of the [FoldingFeature].
     */
    class State private constructor(private val description: String) {

        override fun toString(): String {
            return description
        }

        companion object {
            /**
             * The foldable device is completely open, the screen space that is presented to the
             * user is flat. See the
             * [Posture](https://developer.android.com/guide/topics/ui/foldables#postures)
             * section in the official documentation for visual samples and references.
             */
            val FLAT: State = State("FLAT")

            /**
             * The foldable device's hinge is in an intermediate position between opened and closed
             * state, there is a non-flat angle between parts of the flexible screen or between
             * physical screen panels. See the
             * [Posture](https://developer.android.com/guide/topics/ui/foldables#postures)
             * section in the official documentation for visual samples and references.
             */
            val HALF_OPENED: State = State("HALF_OPENED")
        }
    }

    /**
     * Calculates if a [FoldingFeature] should be thought of as splitting the window into
     * multiple physical areas that can be seen by users as logically separate. Display panels
     * connected by a hinge are always separated. Folds on flexible screens should be treated as
     * separating when they are not [FoldingFeature.State.FLAT].
     *
     * Apps may use this to determine if content should lay out around the [FoldingFeature].
     * Developers should consider the placement of interactive elements. Similar to the case of
     * [FoldingFeature.OcclusionType.FULL], when a feature is separating then consider laying
     * out the controls around the [FoldingFeature].
     *
     * An example use case is to determine if the UI should be split into two logical areas. A
     * media app where there is some auxiliary content, such as comments or description of a video,
     * may need to adapt the layout. The media can be put on one side of the [FoldingFeature] and
     * the auxiliary content can be placed on the other side.
     *
     * @return `true` if the feature splits the display into two areas, `false`
     * otherwise.
     */
    val isSeparating: Boolean

    /**
     * Returns [FoldingFeature.Orientation.HORIZONTAL] if the width is greater than the
     * height, [FoldingFeature.Orientation.VERTICAL] otherwise.
     */
    val orientation: Orientation

    /**
     * Returns the [FoldingFeature.State] for the [FoldingFeature]
     */
    val state: State
}
